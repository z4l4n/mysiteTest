package com.frontendart.locators.registration;

import java.util.Arrays;
import java.util.List;

import com.frontendart.locators.general.GeneralLocatorTypes;

/**
 * Enum class for registration messages
 *
 * @author Zoli
 *
 */
public enum RegistrationMessageTypes implements GeneralLocatorTypes {
    SUCCESSFUL(
            Arrays.asList(
                    "A regisztrációs kérelmet befogadtuk. Nemsokára kap egy levelet, amiben talál egy linket, amin meg kell erősítenie regisztrációját.",
                    "You will be redirected to the login page")),
    WEAK_PASSWORD(Arrays.asList("A jelszó túl gyenge", "Password is too weak")),
    WRONG_CAPTCHA(Arrays.asList("Hibás captcha!", "Invalid captcha!")),
    UNSUCCESSFUL_DATA_POLICY(
            Arrays.asList("Kérjük fogadja el az adatkezelési nyilatkozatot!",
                    "Please accept the data handling policy!")),
    UNSUCCESSFUL_INCORRECT_FIELD(
            Arrays.asList("Néhány szükséges mezőt nem helyesen töltött ki!",
                    "Some required fields were not filled correctly!")),
    UNSUCCESSFUL_SPECIALITIES(
            Arrays.asList("A \"Szakterület\" mezőt ki kell tölteni!",
                    "\"Specialities\" cannot be empty!")),
    UNSUCCESSUL_INSTITUTES_BOTH(
            Arrays.asList("Az \"Munkahely\" és \"Munkahely szövegesen\" mezők közül legalább az egyiket ki kell tölteni!",
                    "\"Institute\" and \"Institute as text\" cannot be empty together!"));
    List<String> names;

    /**
     * Constructor
     *
     * @param itemId
     * @param englishName
     * @param hungarianName
     */
    private RegistrationMessageTypes(final List<String> names) {
        this.names = names;
    }

    /**
     * Returns names
     */
    @Override
    public List<String> getNames() {
        return names;
    }
}
