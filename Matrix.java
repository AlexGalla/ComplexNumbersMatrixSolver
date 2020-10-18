package solver;
import java.util.Scanner;

public class Matrix {
    private Row[] rows;

    int numberOfVariables;
    int numberOfEquations;

    public Matrix(Scanner scanner) {
        numberOfVariables = scanner.nextInt();
        numberOfEquations = scanner.nextInt();
        scanner.nextLine();
        rows = new Row[numberOfEquations];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Row(scanner.nextLine());
        }
    }

    private void sortRows() {
        Row bufferRow;
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows.length; j++) {
                if (rows[i].numberOfLeadZeros() < rows[j].numberOfLeadZeros()) {
                    bufferRow = rows[i];
                    rows[i] = rows[j];
                    rows[j] = bufferRow;
                }
            }
        }
        System.out.println("Matrix after sorting rows: ");
        System.out.println(this.toString());
        this.zeroRowsCheck();
    }

    private void zeroRowsCheck() {
        for (int i = numberOfEquations - 1; i >= 0; i-- ) {
            if (rows[i].numberOfLeadZeros() == numberOfVariables + 1) {
                numberOfEquations = numberOfEquations - 1;
            }
        }
        System.out.println("Matrix after zero rows checking: ");
        System.out.println(this.toString());
    }

    private void rowEchelonForm() {

        for (int i = 0; i < numberOfEquations - 1; i++) {
            for (int j = i + 1; j < numberOfEquations; j++) {
                ComplexNumber multiplier = rows[j].getRowElement(i).complexDivision(rows[i].getRowElement(i));
                for (int k = 0; k < numberOfVariables + 1; k++) {
                    rows[j].getRowElement(k).assignment(rows[j].getRowElement(k).complexSubtraction(rows[i].getRowElement(k).complexMultiplication(multiplier)));
                }
            }
        }
        System.out.println("Row Echelon Form: ");
        System.out.println(this.toString());
        this.zeroRowsCheck();
    }

    private void unitMatrix() {

        System.out.println("Making UnitMatrix....");
        for (int i = numberOfEquations - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                ComplexNumber multiplier = rows[j].getRowElement(i).complexDivision(rows[i].getRowElement(i));
                //row[j].rowElement[i]/row[i].rowElement[i];
                for (int k = 0; k < numberOfVariables + 1; k ++) {
                    rows[j].getRowElement(k).assignment(rows[j].getRowElement(k).complexSubtraction(multiplier.complexMultiplication(rows[i].getRowElement(k))));
                    //row[j].rowElement[k] = row[j].rowElement[k] - multiplier * row[i].rowElement[k];
                }
            }
        }

        System.out.println("Intermediate result: ");
        System.out.println(this.toString());

        for (int i = 0; i < numberOfEquations; i++) {
            ComplexNumber divider = new ComplexNumber(0,0);
            divider.assignment(rows[i].getRowElement(i));//rows[i].getRowElement(i);//double divider = row[i].rowElement[i];
            for (int j = 0; j < numberOfVariables + 1; j++) {
                rows[i].getRowElement(j).assignment(rows[i].getRowElement(j).complexDivision(divider));
                //row[i].rowElement[j] = row[i].rowElement[j] / divider;
            }
        }
        System.out.println("Unit matrix: ");
        System.out.println(this.toString());
    }

    public int hasSolutions() {
        if (numberOfEquations == numberOfVariables) {
            for (int i = 0; i < numberOfEquations; i++) {
                boolean marker = true;
                for (int j = 0; j < numberOfVariables; j++) {
                    if (rows[i].getRowElement(j).isZero()) {
                        continue;
                    } else {
                        marker = false;
                        break;
                    }
                    //marker = marker + this.row[i].rowElement[j];
                }
                if (marker) {
                    return 0; //No solutions
                }
            }
            return 1; //One solution
        } else if (numberOfEquations < numberOfVariables) {
            for (int i = 0; i < numberOfEquations; i++) {
                boolean marker = true;
                for (int j = 0; j < numberOfVariables; j++) {
                    if (rows[i].getRowElement(j).isZero()) {
                        continue;
                    } else {
                        marker = false;
                        break;
                    }
                    //marker = marker + this.row[i].rowElement[j];
                }
                if (marker) {
                    return 0; //No solutions
                }
            }
            return 2; //Infinite solutions
        } else {
            return 0; //No solutions
        }
    }

    public String solveMatrix() {
        this.sortRows();
        this.rowEchelonForm();

        switch (this.hasSolutions()) {
            case 1:
                this.unitMatrix();
                System.out.println("The result matrix:");
                System.out.println(this.toString());

                String result = "";
                for (int i = 0; i < numberOfEquations; i++) {
                    result = result + rows[i].getRowElement(numberOfVariables) + "\n";
                }
                return result;
            case 2:
                return "Infinitely many solutions";
            default:
                return "No solutions";
        }
    }

    @Override
    public String toString() {
        String matrixString = "";
        for (int i = 0; i < numberOfEquations; i++) {
            matrixString = matrixString + rows[i].toString() + "\n";
        }
        return matrixString;
    }
}