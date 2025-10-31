import java.io.*;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.prediction.*;
import hex.genmodel.MojoModel;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    EasyPredictModelWrapper model = new EasyPredictModelWrapper(MojoModel.load("../models/StackedEnsemble_model_R_1761822723646_32290.zip"));

    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter pclass (1,2,3): ");
    String pclass = scanner.nextLine();

    System.out.print("Enter embarked (C/Q/S): ");
    String embarked = scanner.nextLine();

    System.out.print("Enter sex (male/female): ");
    String sex = scanner.nextLine();

    System.out.print("Enter age (e.g., 34): ");
    double age = scanner.nextDouble();

    System.out.print("Enter sibsp (number of siblings/spouses aboard): ");
    double sibsp = scanner.nextInt();

    System.out.print("Enter parch (number of parents/children aboard): ");
    double parch = scanner.nextInt();

    System.out.print("Enter fare (e.g., 7.25): ");
    double fare = scanner.nextDouble();

    RowData row = new RowData();
    row.put("pclass", pclass);
    row.put("embarked", embarked);
    row.put("sex", sex);
    row.put("age", age);
    row.put("sibsp", sibsp);
    row.put("parch", parch);
    row.put("fare", fare);

    BinomialModelPrediction p = model.predictBinomial(row);
    System.out.println("Survivor (1=yes; 0=no): " + p.label);
    System.out.print("Class probabilities: ");
    for (int i = 0; i < p.classProbabilities.length; i++) {
      if (i > 0) {
    System.out.print(",");
      }
      System.out.print(p.classProbabilities[i]);
    }
    System.out.println("");
  }
}
