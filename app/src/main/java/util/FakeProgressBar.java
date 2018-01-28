package util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;

/**
 * Created by Antonio on 27/01/2018.
 */

public class FakeProgressBar extends android.widget.ProgressBar {

    public FakeProgressBar(Context context) {
        super(context);
        setUpView();
    }

    public FakeProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView();
    }

    public FakeProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();
    }

    private void setUpView() {
        this.setVisibility(GONE);
    }

    @Override
    public void setVisibility(int v) {
        // Progressbar should never show
        v = GONE;
        super.setVisibility(v);
    }

    @Override
    public void startAnimation(Animation animation) {
        // Do nothing in test cases, to not block ui thread
    }
}
