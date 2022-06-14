package nl.hhs.apep2122group1;

import org.junit.Assert;
import org.junit.Test;

import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.utils.Validators;

@SuppressWarnings("ConstantConditions")
public class ValidatorsTests {
    @Test
    public void validateStringNotNullOrEmpty_validates_correctly() {
        Assert.assertFalse(Validators.validateStringNotNullOrEmpty(null));
        Assert.assertFalse(Validators.validateStringNotNullOrEmpty(""));
        Assert.assertTrue(Validators.validateStringNotNullOrEmpty("Certainly not empty"));
        Assert.assertTrue(Validators.validateStringNotNullOrEmpty(" "));
    }

    @Test
    public void validatePasswordComplexity_validates_correctly() {
        // too short
        Assert.assertFalse(Validators.validatePasswordComplexity(""));
        Assert.assertFalse(Validators.validatePasswordComplexity(" "));

        // too easy to guess
        Assert.assertFalse(Validators.validatePasswordComplexity("123456"));
        Assert.assertFalse(Validators.validatePasswordComplexity("abcdef"));
        Assert.assertFalse(Validators.validatePasswordComplexity("password"));
        Assert.assertFalse(Validators.validatePasswordComplexity("batman"));
        Assert.assertFalse(Validators.validatePasswordComplexity("zzzzzzzzzzzzzz"));
        Assert.assertFalse(Validators.validatePasswordComplexity("000000"));

        // strong enough
        Assert.assertTrue(Validators.validatePasswordComplexity("918461"));
        Assert.assertTrue(Validators.validatePasswordComplexity("v3ry$tr0ng"));
        Assert.assertTrue(Validators.validatePasswordComplexity("myL1ttl3Pa$$w0rd"));
        Assert.assertTrue(Validators.validatePasswordComplexity("superman"));
    }

    @Test
    public void validateStringDoesNotBeginOrEndWithWhitespace_validates_correctly() {
        Assert.assertTrue(Validators.validateStringDoesNotBeginOrEndWithWhitespace(""));
        Assert.assertTrue(Validators.validateStringDoesNotBeginOrEndWithWhitespace("1234"));
        Assert.assertTrue(Validators.validateStringDoesNotBeginOrEndWithWhitespace("hoedje van papier"));

        Assert.assertFalse(Validators.validateStringDoesNotBeginOrEndWithWhitespace("\ttab test"));
        Assert.assertFalse(Validators.validateStringDoesNotBeginOrEndWithWhitespace("carriage return test\r"));
        Assert.assertFalse(Validators.validateStringDoesNotBeginOrEndWithWhitespace("\nnewline test"));
        Assert.assertFalse(Validators.validateStringDoesNotBeginOrEndWithWhitespace("  space test"));
    }

    @Test
    public void validateStringDoesNotContainWhitespace_validates_correctly() {
        Assert.assertTrue(Validators.validateStringDoesNotContainWhitespace(""));
        Assert.assertTrue(Validators.validateStringDoesNotContainWhitespace("NoWhitespace"));

        Assert.assertFalse(Validators.validateStringDoesNotContainWhitespace("Yes Whitespace"));
        Assert.assertFalse(Validators.validateStringDoesNotContainWhitespace(" YesWhitespace"));
        Assert.assertFalse(Validators.validateStringDoesNotContainWhitespace("Yes\tWhitespace"));
        Assert.assertFalse(Validators.validateStringDoesNotContainWhitespace("\nYesWhitespace\n"));
        Assert.assertFalse(Validators.validateStringDoesNotContainWhitespace("\tYesWhitespace"));
        Assert.assertFalse(Validators.validateStringDoesNotContainWhitespace(" "));
    }

    @Test
    public void validateNewLabelTitleUnique_validates_correctly() {
        Label[] labels = getLabels();

        Assert.assertFalse(Validators.validateNewLabelTitleUnique("school", labels));
        Assert.assertFalse(Validators.validateNewLabelTitleUnique("School", labels));
        Assert.assertFalse(Validators.validateNewLabelTitleUnique("Knitting club", labels));
        Assert.assertFalse(Validators.validateNewLabelTitleUnique("KnItTiNg ClUb", labels));

        Assert.assertTrue(Validators.validateNewLabelTitleUnique("SchoolX", labels));
        Assert.assertTrue(Validators.validateNewLabelTitleUnique("XSchool", labels));
        Assert.assertTrue(Validators.validateNewLabelTitleUnique("XSchool", labels));
        Assert.assertTrue(Validators.validateNewLabelTitleUnique("Kn1tt1ng Club", labels));
    }

    private Label[] getLabels() {
        Label label1 = new Label("School", "#123456", "AvengersFanboy");
        Label label2 = new Label("Work", "#123456", "AvengersFanboy");
        Label label3 = new Label("Groceries", "#123456", "AvengersFanboy");
        Label label4 = new Label("Pets", "#123456", "AvengersFanboy");
        Label label5 = new Label("Knitting club", "#123456", "AvengersFanboy");

        return new Label[] { label1, label2, label3, label4, label5 };
    }
}