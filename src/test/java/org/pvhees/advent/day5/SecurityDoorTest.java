package org.pvhees.advent.day5;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SecurityDoorTest {

    @Test
    public void example1() throws Exception {
        assertThat(SecurityDoor.getPasswordFor("abc"), is("18f47a30"));
    }

    @Test
    public void opgave1() throws Exception {
        System.out.println("The first password is "+SecurityDoor.getPasswordFor("uqwqemis"));
    }

    @Test
    public void example2() throws Exception {
        assertThat(SecurityDoor.getPasswordForV2("abc"), is("05ace8e3"));
    }

    @Test
    public void opgave2() throws Exception {
        System.out.println("The second password is "+SecurityDoor.getPasswordForV2("uqwqemis"));
    }

}
