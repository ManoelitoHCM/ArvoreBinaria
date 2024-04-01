import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("Insira valor para a quantidade de nós da árvore binária: ");
        int size = BinaryTree.scan.nextInt();

        System.out.println("Insira valor mínimo que será gerado para preencher a árvore binária: ");
        int minValue = BinaryTree.scan.nextInt();

        System.out.println("Insira valor máximo que será gerado para preencher a árvore binária: ");
        int maxValue = BinaryTree.scan.nextInt();

        ArrayList<Integer> values = BinaryTree.fillVector(size, minValue, maxValue);

        BinaryTree tree = new BinaryTree(values);
/*
        System.out.println("Insira novo valor para a árvore: ");
        int newValue = BinaryTree.scan.nextInt();
        tree.insertInTree(newValue);
*/
        System.out.println("Root: " + tree.root.value);

        tree.binaryTreeTraversal();

        System.out.println("Is the tree balanced? " + tree.isBalanced());
    }
}