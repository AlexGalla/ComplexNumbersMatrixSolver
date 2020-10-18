package solver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexNumber {
    private double realPart = -20;
    private double imaginaryPart = 10;

    public double getRealPart() {
        return realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public ComplexNumber (double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public ComplexNumber(String complexNumberString) {
        Pattern imaginaryPartPattern = Pattern.compile("[\\+|-]?(\\d+(\\.\\d+)?)?i");
        Matcher matcher = imaginaryPartPattern.matcher(complexNumberString);
        if (matcher.find()) {
            String matchingString = matcher.group();
            String imaginaryString;
            String realString = complexNumberString.replace(matchingString, "");
            if (realString.equals("")) {
                realString ="0";
            }

            if (matchingString.replace("i", "").equals("")) {
                imaginaryString = "1";
            } else if (matchingString.replace("i", "").length() == 1 && (matchingString.replace("i", "").contains("+") || matchingString.replace("i", "").contains("-"))) {
                imaginaryString =  matchingString.replace("i", "") + "1";
            } else {
                imaginaryString = matchingString.replace("i", "");
            }
            //System.out.println("Real string: " + realString);
            //System.out.println("Imaginary string: " + imaginaryString);
            realPart = Double.parseDouble(realString);
            imaginaryPart = Double.parseDouble(imaginaryString.replace("i", ""));
        } else {
            imaginaryPart = 0.0;
            realPart = Double.parseDouble(complexNumberString);
        }
    }

    public boolean isZero() {
        if (realPart == 0 && imaginaryPart == 0) {
            return true;
        } else {
            return false;
        }
    }

    public  ComplexNumber complexAddition(ComplexNumber complexNumber) {
        return (new ComplexNumber(this.realPart + complexNumber.getRealPart(), this.imaginaryPart + complexNumber.getImaginaryPart()));
    }

    public ComplexNumber  complexSubtraction(ComplexNumber complexNumber) {
        return (new ComplexNumber(this.realPart - complexNumber.getRealPart(), this.imaginaryPart - complexNumber.getImaginaryPart()));
    }

    public ComplexNumber complexMultiplication(ComplexNumber complexNumber) {
        double real = this.realPart * complexNumber.getRealPart() - this.imaginaryPart * complexNumber.imaginaryPart;
        double imaginary = this.imaginaryPart * complexNumber.getRealPart() + complexNumber.getImaginaryPart() * this.realPart;
        return new ComplexNumber(real, imaginary);
    }

    public ComplexNumber complexDivision(ComplexNumber complexNumber) {
        double real = (this.realPart * complexNumber.getRealPart() + this.imaginaryPart * complexNumber.getImaginaryPart())/
                (complexNumber.getRealPart() * complexNumber.getRealPart() + complexNumber.getImaginaryPart() * complexNumber.getImaginaryPart());
        double imaginary = (this.imaginaryPart * complexNumber.getRealPart() - this.realPart * complexNumber.getImaginaryPart())/
                (complexNumber.getRealPart() * complexNumber.getRealPart() + complexNumber.getImaginaryPart() * complexNumber.getImaginaryPart());
        return new ComplexNumber(real, imaginary);
    }

    public void assignment(ComplexNumber complexNumber) {
        this.realPart = complexNumber.getRealPart();
        this.imaginaryPart = complexNumber.getImaginaryPart();
        //return this;
    }


    @Override
    public String toString() {
        if (imaginaryPart == 0 ) {
            //return Double.toString(realPart);
            return String.format("%.4f", realPart);
        } else if (realPart == 0 && imaginaryPart!=0) {
            //return imaginaryPart + "i";
            return String.format("%.4f", imaginaryPart) + "i";
        } else {
            if (imaginaryPart < 0) {
                //return realPart + imaginaryPart + "i";
                return String.format("%.4f", realPart) + String.format("%.4f", imaginaryPart) + "i";
            } else {
                //return realPart + "+" + imaginaryPart + "i";
                return String.format("%.4f", realPart)+ "+" + String.format("%.4f", imaginaryPart) + "i";
            }
        }
    }
}
