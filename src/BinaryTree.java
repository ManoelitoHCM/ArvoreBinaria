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
        buildTree(values);
    }

    private void buildTree(ArrayList<Integer> values) {

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

    public void getInformationOfNode(int value) {
        Node node = findNodeWithValue(root, value);

        if (node != null) {
            System.out.println("Valor " + value + " encontrado na árvore.");
            System.out.println("Altura do nó: " + getHeight(node));

            if (node.leftChild != null) {
                System.out.println("Filho à esquerda: " + node.leftChild.value);
            } else {
                System.out.println("Não há filho à esquerda.");
            }

            if (node.rightChild != null) {
                System.out.println("Filho à direita: " + node.rightChild.value);
            } else {
                System.out.println("Não há filho à direita.");
            }
        } else {
            System.out.println("Valor " + value + " não encontrado na árvore.");
        }
    }

    private Node findNodeWithValue(Node currentNode, int value) {

        if (currentNode == null || currentNode.value == value) {
            return currentNode;
        }

        if (value > currentNode.value) {
            return findNodeWithValue(currentNode.rightChild, value);
        }
        else {
            return findNodeWithValue(currentNode.leftChild, value);
        }
    }

    // os métodos abaixo servem para calcular altura, nivel máximo, verificar se árvore está balanceada e realizar
    // seu balanceamento

    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.leftChild);
        int rightHeight = getHeight(node.rightChild);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int leftHeight = getHeight(node.leftChild);
        int rightHeight = getHeight(node.rightChild);

        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(node.leftChild) && isBalanced(node.rightChild)) {
            return true;
        }
        return false;
    }

    public void balanceTree() {
        root = balanceSubTree(root); // Call balanceSubTree on the root node
    }

    private Node balanceSubTree(Node node) {
        if (node == null) {
            return null;
        }

        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.leftChild) >= 0) {
                return rotateRight(node);
            } else {
                node.leftChild = rotateLeft(node.leftChild);
                return rotateRight(node);
            }
        } else if (balanceFactor < -1) {
            if (getBalanceFactor(node.rightChild) <= 0) {
                return rotateLeft(node);
            } else {
                node.rightChild = rotateRight(node.rightChild);
                return rotateLeft(node);
            }
        }

        node.leftChild = balanceSubTree(node.leftChild);
        node.rightChild = balanceSubTree(node.rightChild);

        return node;
    }

    private int getBalanceFactor(Node node) {
        if(node == null) {
            return 0;
        }
        return getHeight(node.leftChild) - getHeight(node.rightChild);
    }

    public int getMaxLevel(Node node) {
        return (int) Math.pow(2, getHeight(node));
    }

    public boolean isFull(Node node){
        int height = getHeight(root);
        int amountOfNodes = size;

        int maxNodesAllowed = getMaxLevel(node) - 1;

        return amountOfNodes == maxNodesAllowed;
    }

    private Node rotateRight(Node y) {
        Node x = y.leftChild;
        Node T2 = x.rightChild;

        x.rightChild = y;
        y.leftChild = T2;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.rightChild;
        Node T2 = y.leftChild;

        y.leftChild = x;
        x.rightChild = T2;

        return y;
    }

    // método de inserção na árvore, fazendo comparação inicialmente para verificar se ela
    // está vazia ou não (raiz == null) e logo após, fazendo percurso para verificar em que
    // subárvore o novo valor deve ser alocado

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

    // 4 métodos para percorrer a árvore, in order, pre order e post order, segundo escolha do usuário no main

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

    // método para preencher o vetor com valores aleatórios a partir de dois limites (min e max value). Ele chama
    // método de ordenação logo após, para facilitar a construção da árvore binária de forma mais balanceada a partir
    // de um valor do meio do vetor (que distribuiria de forma uniforme os valores para as duas subárvores da esquerda
    // e da direita)
    public static ArrayList<Integer> fillVector(int size, int minValue, int maxValue){
        ArrayList<Integer> values = new ArrayList<>(size);

        for (int i = 0; i < size; i++){

            int randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;

            if (!values.contains(randomNumber)) {
                values.add(randomNumber);
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

        return values;
    }

}
