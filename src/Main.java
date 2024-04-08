import java.util.ArrayList;

//Nome: MANOELITO HOLANDA CASTELO MATOS
//Matricula: 56000477
//IDE: IntelliJ

public class Main {
    public static void main(String[] args) {
        BinaryTree binaryTree = null;

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Criar árvore binária preenchendo manualmente");
            System.out.println("2. Criar árvore binária preenchendo automaticamente com valores aleatórios");
            System.out.println("3. Inserir valor na árvore");
            System.out.println("4. Obter informações de um nó");
            System.out.println("5. Percorrer a árvore");
            System.out.println("6. Verificar se a árvore está balanceada");
            System.out.println("7. Balancear a árvore");
            System.out.println("8. Sair");

            int choice = BinaryTree.scan.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Insira o tamanho do vetor:");
                    int size = BinaryTree.scan.nextInt();
                    ArrayList<Integer> manualValues = new ArrayList<>();
                    System.out.println("Insira os valores separados por espaço:");
                    for (int i = 0; i < size; i++) {
                        manualValues.add(BinaryTree.scan.nextInt());
                    }
                    binaryTree = new BinaryTree(manualValues);
                    break;
                case 2:
                    System.out.println("Insira o tamanho do vetor:");
                    int sizeRandom = BinaryTree.scan.nextInt();
                    System.out.println("Insira o valor mínimo:");
                    int minValue = BinaryTree.scan.nextInt();
                    System.out.println("Insira o valor máximo:");
                    int maxValue = BinaryTree.scan.nextInt();
                    ArrayList<Integer> randomValues = BinaryTree.fillVector(sizeRandom, minValue, maxValue);
                    binaryTree = new BinaryTree(randomValues);
                    break;
                case 3:
                    if (binaryTree == null) {
                        System.out.println("Crie uma árvore primeiro.");
                        break;
                    }
                    System.out.println("Insira o valor a ser inserido:");
                    int newValue = BinaryTree.scan.nextInt();
                    binaryTree.insertInTree(newValue);
                    break;
                case 4:
                    if (binaryTree == null) {
                        System.out.println("Crie uma árvore primeiro.");
                        break;
                    }
                    System.out.println("Insira o valor do nó a ser consultado:");
                    int nodeValue = BinaryTree.scan.nextInt();
                    binaryTree.getInformationOfNode(nodeValue);
                    break;
                case 5:
                    if (binaryTree == null) {
                        System.out.println("Crie uma árvore primeiro.");
                        break;
                    }
                    binaryTree.binaryTreeTraversal();
                    break;
                case 6:
                    if (binaryTree == null) {
                        System.out.println("Crie uma árvore primeiro.");
                        break;
                    }
                    System.out.println("A árvore está balanceada? " + binaryTree.isBalanced());
                    break;
                case 7:
                    if (binaryTree == null) {
                        System.out.println("Crie uma árvore primeiro.");
                        break;
                    }
                    binaryTree.balanceTree();
                    System.out.println("Árvore balanceada.");
                    break;
                case 8:
                    System.out.println("Encerrando o programa.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");

            }
        }
    }
}