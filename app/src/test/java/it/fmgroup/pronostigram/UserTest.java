package it.fmgroup.pronostigram;

import org.junit.Test;

import model.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UserTest {

    @Test
    public void TestGettersSetters() {
        User u = new User();

        u.setNome("Antonio");
        assertThat(u.getNome().equals("Antonio"), is(true));
    }

}