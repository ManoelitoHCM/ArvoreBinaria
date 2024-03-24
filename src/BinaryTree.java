import java.util.Scanner;
import java.util.Random;

public class BinaryTree {
    private static Scanner scan = new Scanner (System.in);
    private static Random random = new Random ();

    Node root;
    int size;
    int[] values = new int[size];

    public BinaryTree(int[] values){
        this.values = values;
        this.root = buildTree(0, values.length - 1);
    }

/*
    private Node buildTree(int begin, int end) {
        if (begin > end) {
            return null;
        }

        int middle = (begin + end) / 2;
        Node node = new Node(values[middle]);

        node.leftChild = buildTree (begin, middle - 1);
        node.rightChild = buildTree (middle + 1, end);

        return node;
    }
*/

    public Node insertInTree(int value) {
        Node newNode = new Node(value);

        if(root == null) {
            root = newNode;
            return root;
        }

        Node parentNode = null;
        Node currentNode = root;

        while(currentNode != null) {
            parentNode = currentNode;

            if(value < currentNode.value) {
                currentNode.leftChild = currentNode(value);
            } else if(currentNode.rightChild == null) {
                currentNode.rightChild = currentNode;
            }
        }



        return currentNode;
    }

    public int[] anyOrderTraversal(Node node) {
        int[] result = new int[size];

        inOrderTraversalRecursive(node, result);
/*
      preOrderTraversalRecursive(node, result);
      postOrderTraversalRecursive(node, result);
*/

        return result;
    }

    private void inOrderTraversalRecursive(Node node, int[] result) {
        if (node != null) {
            inOrderTraversalRecursive (node.leftChild, result);
            System.out.print(node.value + " ");
            inOrderTraversalRecursive (node.rightChild, result);
        }
    }

/*
    private void preOrderTraversalRecursive(Node node, int[] result) {
        if (node != null) {
            System.out.print(node.value + " ");
            inOrderTraversalRecursive (node.leftChild, result);
            inOrderTraversalRecursive (node.rightChild, result);
        }
    }

    private void postOrderTraversalRecursive(Node node, int[] result) {
        if (node != null) {
            inOrderTraversalRecursive (node.leftChild, result);
            inOrderTraversalRecursive (node.rightChild, result);
            System.out.print(node.value + " ");
        }
    }
*/

    public static int[] fillVector(int size, int minValue, int maxValue){
        int[] values = new int[size];

        for (int i = 0; i < size; i++){
            values[i] = random.nextInt(maxValue - minValue + 1) + minValue;
        }

        System.out.println("Vetor preenchido: ");

        for (int value : values){
            System.out.print(value + " ");
        }
        System.out.println();
        return values;
    }

    //Selection sort
    public static int[] sorter(int[] values){
        for (int i = 0; i < values.length - 1; i++) {
            int minIndex = i;

            for (int j = minIndex + 1; j < values.length; j++) {
                if (values[j] < values[minIndex]) {
                    minIndex = j;
                }
            }
            int aux = values[i];
            values[i] = values[minIndex];
            values[minIndex] = aux;
            }
        return values;
    }

    public static void main(String[] args) {

        System.out.println("Insira valor para a altura da árvore binária: ");
        int size = scan.nextInt ();

        System.out.println("Insira valor mínimo que será gerado para preencher a árvore binária: ");
        int minValue = scan.nextInt ();

        System.out.println("Insira valor máximo que será gerado para preencher a árvore binária: ");
        int maxValue = scan.nextInt ();

        int[] values = BinaryTree.fillVector (size, minValue, maxValue);

        BinaryTree tree = new BinaryTree(values);

        System.out.println("inOrderTraversal: ");
        tree.anyOrderTraversal(tree.root);
    }
}
