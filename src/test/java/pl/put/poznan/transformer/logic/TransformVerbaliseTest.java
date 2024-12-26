package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransformVerbaliseTest {

    private TextTransformerInterface transformer;

    @BeforeEach
    public void setUp() {
        transformer = new TransformVerbalise(new TextTransformer());
    }

    @Test
    @DisplayName("Testy dla specyficznych przypadków transformacji na słowa")
    public void testSpecyficCases() {
        Assertions.assertEquals("zero", transformer.transform("0"));
        Assertions.assertEquals("zero", transformer.transform("0.0"));
        Assertions.assertEquals("zero", transformer.transform("0.00"));
        Assertions.assertEquals("tysiąc", transformer.transform("1000"));
        Assertions.assertEquals("", transformer.transform(""));
    }

    @Test
    @DisplayName("Test pojedynczych cyfr")
    public void testSingleDigits() {
        Assertions.assertEquals("jeden dwa trzy", transformer.transform("1 2 3"));
        Assertions.assertEquals("cztery pięć sześć", transformer.transform("4 5 6"));
        Assertions.assertEquals("siedem osiem dziewięć", transformer.transform("7 8 9"));
    }

    @Test
    @DisplayName("Test liczb dwucyfrowych")
    public void testTwoDigitNumbers() {
        Assertions.assertEquals("dziesięć jedenaście dwanaście", transformer.transform("10 11 12"));
        Assertions.assertEquals("dwadzieścia trzydzieści czterdzieści", transformer.transform("20 30 40"));
        Assertions.assertEquals("pięćdziesiąt sześćdziesiąt siedemdziesiąt", transformer.transform("50 60 70"));
        Assertions.assertEquals("osiemdziesiąt dziewięćdziesiąt", transformer.transform("80 90"));
        Assertions.assertEquals("dwadzieścia trzy trzydzieści pięć czterdzieści siedem", transformer.transform("23 35 47"));
        Assertions.assertEquals("pięćdziesiąt sześć sześćdziesiąt osiem osiemdziesiąt dziewięć dziewięćdziesiąt dwa", transformer.transform("56 68 89 92"));
    }

    @Test
    @DisplayName("Test liczb trzycyfrowych")
    public void testThreeDigitNumbers() {
        Assertions.assertEquals("sto dwieście trzysta", transformer.transform("100 200 300"));
        Assertions.assertEquals("czterysta pięćset sześćset", transformer.transform("400 500 600"));
        Assertions.assertEquals("siedemset osiemset dziewięćset", transformer.transform("700 800 900"));
        Assertions.assertEquals("sto jedenaście sto dwadzieścia trzy", transformer.transform("111 123"));
        Assertions.assertEquals("dwieście trzydzieści cztery dwieście pięćdziesiąt sześć", transformer.transform("234 256"));
        Assertions.assertEquals("trzysta czterdzieści pięć trzysta sześćdziesiąt siedem", transformer.transform("345 367"));
        Assertions.assertEquals("czterysta pięćdziesiąt sześć czterysta siedemdziesiąt osiem", transformer.transform("456 478"));
    }

    @Test
    @DisplayName("Test liczb z częścią ułamkową")
    public void testDecimalNumbers() {
        Assertions.assertEquals("jedna setna", transformer.transform("0.01"));
        Assertions.assertEquals("dwie setne", transformer.transform("0.02"));
        Assertions.assertEquals("trzy setne", transformer.transform("0.03"));
        Assertions.assertEquals("cztery setne", transformer.transform("0.04"));
        Assertions.assertEquals("pięć setnych", transformer.transform("0.05"));
        Assertions.assertEquals("dziesięć setnych", transformer.transform("0.1"));
        Assertions.assertEquals("sześć setnych", transformer.transform("0.06"));
        Assertions.assertEquals("dziesięć setnych", transformer.transform("0.10"));
        Assertions.assertEquals("jedenaście setnych", transformer.transform("0.11"));
        Assertions.assertEquals("dwanaście setnych", transformer.transform("0.12"));
        Assertions.assertEquals("trzynaście setnych", transformer.transform("0.13"));
        Assertions.assertEquals("czterdzieści dwie setne", transformer.transform("0.42"));
        Assertions.assertEquals("siedemdziesiąt osiem setnych", transformer.transform("0.78"));
        Assertions.assertEquals("dziewięćdziesiąt pięć setnych", transformer.transform("0.95"));
        Assertions.assertEquals("jeden i dwadzieścia trzy setne", transformer.transform("1.23"));
        Assertions.assertEquals("dwa i pięćdziesiąt setnych", transformer.transform("2.5"));
        Assertions.assertEquals("dwa i pięćdziesiąt sześć setnych", transformer.transform("2.56"));
        Assertions.assertEquals("dziesięć i pięćdziesiąt sześć setnych", transformer.transform("10.56"));
        Assertions.assertEquals("dwadzieścia trzy i czterdzieści pięć setnych", transformer.transform("23.45"));
        Assertions.assertEquals("sześćdziesiąt siedem i osiemdziesiąt dziewięć setnych", transformer.transform("67.89"));
        Assertions.assertEquals("dwieście siedemdziesiąt osiem i dziewięćdziesiąt dziewięć setnych", transformer.transform("278.99"));
        Assertions.assertEquals("trzysta trzydzieści trzy i trzydzieści trzy setne", transformer.transform("333.33"));
        Assertions.assertEquals("osiemset sześćdziesiąt i dwadzieścia trzy setne", transformer.transform("860.23"));
        Assertions.assertEquals("tysiąc i dziewięćdziesiąt dziewięć setnych", transformer.transform("1000.99"));
    }

    @Test
    @DisplayName("Test z liczbami i tekstem")
    public void testNumbersWithText() {
        Assertions.assertEquals("test jeden test dwa test trzy", transformer.transform("test 1 test 2 test 3"));
        Assertions.assertEquals("test sto dwadzieścia trzy test pięćset sześćdziesiąt siedem", transformer.transform("test 123 test 567"));
        Assertions.assertEquals("test zero test dziesięć test dwadzieścia trzy", transformer.transform("test 0 test 10 test 23"));
        Assertions.assertEquals("test sto dwadzieścia trzy test zero test pięćset sześćdziesiąt siedem", transformer.transform("test 123 test 0 test 567"));
        Assertions.assertEquals("test zero test", transformer.transform("test 0 test"));
        Assertions.assertEquals("przy100", transformer.transform("przy100"));
        Assertions.assertEquals("Su27 F16 2Pac PS5 H2O", transformer.transform("Su27 F16 2Pac PS5 H2O"));
    }

    @Test
    @DisplayName("Test liczb spoza zakresu")
    public void testOutOfRange() {
        Assertions.assertEquals("1234", transformer.transform("1234"));
        Assertions.assertEquals("1.234", transformer.transform("1.234"));
        Assertions.assertEquals("0.000", transformer.transform("0.000"));
    }
}