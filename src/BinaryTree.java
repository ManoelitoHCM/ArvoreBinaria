import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class BinaryTree {
    public static Scanner scan = new Scanner (System.in);
    public static Random random = new Random ();

    Node root;
    int size;
    ArrayList<Integer> values = new ArrayList<>();

    public BinaryTree(ArrayList<Integer> values){
        this.values = values;
        this.size = values.size();
        this.root = null;
        buildTree();
    }

    private void buildTree() {
        int middle = (values.size()) / 2;

        root = new Node(values.get(middle));

        Node currentNode = root;

        for (int index = 0; index < size; index++) {
            if(values.get(index) < currentNode.value) {
                currentNode.leftChild = new Node(values.get(index));
                currentNode = currentNode.leftChild;
            } else if(values.get(index) > currentNode.value) {
                currentNode.rightChild = new Node(values.get(index));
                currentNode = currentNode.rightChild;
            }
        }
    }

    public Node insertInTree(int newValue) {
        Node newNode = new Node(newValue);

        if(root == null) {
            root = newNode;
            return root;
        }

        Node parentNode = null;
        Node currentNode = root;

        while(currentNode != null) {
            parentNode = currentNode;
            if(newValue < currentNode.value) {
                currentNode = currentNode.leftChild;
            } else if(newValue > currentNode.value) {
                currentNode = currentNode.rightChild;
            } else {
                return null;
            }
        }

        if (newValue < parentNode.value) {
            parentNode.leftChild = newNode;
        } else {
            parentNode.rightChild = newNode;
        }

        return newNode;
    }

    public void binaryTreeTraversal() {
        System.out.println("Insira 1 para percorrer a árvore em in order, 2 para pre order e 3 para post order: ");
        int k = BinaryTree.scan.nextInt();

        switch (k) {
            case 1:
                System.out.println("inOrderTraversal: ");
                inOrderTraversal(root);
                break;
            case 2:
                System.out.println("preOrderTraversal: ");
                preOrderTraversal(root);
                break;
            case 3:
                System.out.println("postOrderTraversal: ");
                postOrderTraversal(root);
                break;
        }
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.leftChild);
            System.out.print(node.value + " ");
            inOrderTraversal(node.rightChild);
        }
    }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrderTraversal(node.leftChild);
            preOrderTraversal(node.rightChild);
        }
    }

    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.leftChild);
            postOrderTraversal(node.rightChild);
            System.out.print(node.value + " ");
        }
    }

    public static ArrayList<Integer> fillVector(int size, int minValue, int maxValue){
        ArrayList<Integer> values = new ArrayList<>(size);

        for (int i = 0; i < size; i++){

            int randNB = random.nextInt(maxValue - minValue + 1) + minValue;

            if (!values.contains(randNB)) {
                values.add(randNB);
            }
        }

        System.out.println("Vetor preenchido: ");

        for (int value : values){
            System.out.print(value + " ");
        }
        System.out.println();

        ArrayList<Integer> sortedValues = Sorter.selectionSort(values);

        System.out.println("Vetor ordenado: ");

        for (int value : sortedValues){
            System.out.print(value + " ");
        }
        System.out.println();

        return sortedValues;
    }
}
