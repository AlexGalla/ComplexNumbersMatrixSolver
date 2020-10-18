package solver;

public class Row {
    private ComplexNumber[] rowElements;

    /*public Row(int size) {
        this.rowElement = new ComplexNumber[size];
    }*/

    public Row(String str) {
        String[] rowString = str.split(" ");
        rowElements = new ComplexNumber[rowString.length];
        for (int i = 0; i < rowElements.length; i++) {
            rowElements[i] = new ComplexNumber(rowString[i]);
        }
    }

    public ComplexNumber getRowElement(int i) {
        return rowElements[i];
    }

    public int numberOfLeadZeros(){
        int counter = 0;
        for (int i = 0; i < rowElements.length; i++) {
            if (rowElements[i].isZero()) {
                counter++;
            } else {
                break;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        String rowString = "";
        for (int i = 0; i < rowElements.length; i++) {
            rowString = rowString + rowElements[i].toString() + " ";
        }
        return rowString;
    }

}
