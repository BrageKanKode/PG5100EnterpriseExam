package no.enterprise.exam.selenium.po;

/*
    https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/po/SignUpPO.java
 */

import no.enterprise.exam.selenium.PageObject;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignUpPO extends LayoutPO {

    public SignUpPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Sign Up");
    }

    public IndexPO signUP(String userID, String password) {
        setText("username", userID);
        setText("password", password);
        clickAndWait("signUpBtn");

        IndexPO indexPO = new IndexPO(this);
        assertTrue(getDriver().getTitle().contains("Home page"));

        return indexPO;
    }


}
