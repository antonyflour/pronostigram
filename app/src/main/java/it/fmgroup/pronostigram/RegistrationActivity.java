package it.fmgroup.pronostigram;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.User;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private static final String TAGEXCEPTION = "ExceptionTag";
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    // UI references.
    private AutoCompleteTextView nameView;
    private AutoCompleteTextView surnameView;
    private AutoCompleteTextView emailView;
    private AutoCompleteTextView confirmEmailView;
    private EditText passwordView;
    private EditText confirmPasswordView;
    public View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameView = (AutoCompleteTextView) findViewById(R.id.text_nome);
        surnameView = (AutoCompleteTextView) findViewById(R.id.text_cognome);
        emailView = (AutoCompleteTextView) findViewById(R.id.text_email);
        confirmEmailView = (AutoCompleteTextView) findViewById(R.id.text_email_confirm);


        passwordView = (EditText) findViewById(R.id.text_password);
        confirmPasswordView = (EditText) findViewById(R.id.text_password_confirm);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        confirmPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptRegistration();
                    return true;
                }
                return false;
            }
        });

        Button buttonConfirm = (Button) findViewById(R.id.button_reg_confirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegistration();
            }
        });

        mLoginFormView = findViewById(R.id.registration_form);

        mProgressView = findViewById(R.id.registration_progress);
    }


    private void attemptRegistration() {

        // Reset errors.
        nameView.setError(null);
        surnameView.setError(null);
        emailView.setError(null);
        confirmEmailView.setError(null);
        passwordView.setError(null);
        confirmPasswordView.setError(null);

        // Store values at the time of the registration attempt.
        String name = nameView.getText().toString();
        String surname = surnameView.getText().toString();
        String email = emailView.getText().toString();
        String confirmEmail = confirmEmailView.getText().toString();
        String password = passwordView.getText().toString();
        String confirmPassword = confirmPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for the existence of the info
        if (TextUtils.isEmpty(name)) {
            nameView.setError(getString(R.string.error_field_required));
            focusView = nameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(surname)) {
            surnameView.setError(getString(R.string.error_field_required));
            focusView = surnameView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        }

        if (!TextUtils.equals(email, confirmEmail)) {
            confirmEmailView.setError(getString(R.string.error_invalid_confirm_email));
            focusView = confirmEmailView;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        if (!TextUtils.equals(password, confirmPassword)) {
            confirmPasswordView.setError(getString(R.string.error_invalid_confirm_password));
            focusView = confirmPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            createAccount(email,password, name, surname);

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    private void createAccount(final String mEmail, final String mPassword, final String mName, final String mSurname) {
        Log.d(TAG, "createAccount:" + mEmail);
        showProgress(true);
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            user = mAuth.getCurrentUser();
                            User u = new User(generateUsername(mEmail),mEmail,mPassword,mName,mSurname,0,0);
                            Log.d("CIAO",u.toString());
                            database.getReference("ciao").setValue("ciao");
                            reference = database.getReference("users/");
                            reference.child(generateUsername(mEmail)).setValue(u);
                            Log.d("ciao", "ho scritto nel db");
                            startActivity(new Intent(RegistrationActivity.this,FeedActivity.class));
                        } else {
                            showProgress(false);
                            try {
                                throw task.getException();
                            } catch(FirebaseAuthWeakPasswordException e) {
                                confirmPasswordView.setError(getString(R.string.error_weak_password));
                                confirmPasswordView.requestFocus();
                                Log.e(TAGEXCEPTION, e.getMessage());
                            } catch(FirebaseAuthInvalidCredentialsException e) {
                                emailView.setError(getString(R.string.error_invalid_email));
                                emailView.requestFocus();
                                Log.e(TAGEXCEPTION, e.getMessage());
                            } catch(FirebaseAuthUserCollisionException e) {
                                emailView.setError(getString(R.string.error_user_exists));
                                emailView.requestFocus();
                                Log.e(TAGEXCEPTION, e.getMessage());
                            } catch(Exception e) {
                                Log.e(TAGEXCEPTION, e.getMessage());
                            }
                        }

                    }
                });
    }

    private String generateUsername(String mEmail){
        String username = mEmail.substring(0,mEmail.indexOf('@'));
        username = username.replaceAll("\\.", "-");
        return username;
    }
}
