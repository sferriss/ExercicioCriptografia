package criptografia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Criptografia {

    private char[] array;
    private int chave = 0;

    public Criptografia() {
    }

    public void cifraSenha(String senha) {
        int nChave = 0;
        for (int i = 0; i < senha.length(); i++) {
            nChave += (int) senha.charAt(i);
        }
        this.chave = nChave % 256;
    }

    public String converteDecimalEmBinario(int i) {
        return Integer.toBinaryString(i);
    }

    public void carregaArquivo(String arquivo) {
        String conteudo = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(arquivo));
            String str;
            while ((str = in.readLine()) != null) {
                conteudo += str;
                conteudo += " ";
            }
            in.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        array(conteudo);
    }

//Recebe o texto e armazena no array
    public void array(String txt) {
        this.array = new char[txt.length()];
        for (int i = 0; i < txt.length(); i++) {
            array[i] = txt.charAt(i);
        }
        oneCrip();
    }

    //faz a primeira criptografia
    public void oneCrip() {
        int cont = 0;
        int aux = 0;
        for (int i = 0; i < array.length; i++) {
            if (cont == 0) {
                aux = (int) array[i] + 2;
                this.array[i] = (char) aux;
            }
            if (cont == 1) {
                aux = (int) array[i] + 5;
                this.array[i] = (char) aux;
            }
            if (cont == 2) {
                aux = (int) array[i] + 7;
                this.array[i] = (char) aux;
            }
            if (cont == 2) {
                cont = 0;
            } else {
                ++cont;
            }
        }
        System.out.println(this.array);
        twoCrip();

    }

    //faz a segunda criptografia
    public void twoCrip() {
        int cont = 0;
        Stack<Character> pilhaAux = new StaticStack<>(2);
        for (int i = 0; i < this.array.length; i++) {
            if (!pilhaAux.isFull()) {
                pilhaAux.push(array[i]);
            }
            if (pilhaAux.isFull()) {
                while (!pilhaAux.isEmpty()) {
                    array[cont] = pilhaAux.pop();
                    cont++;
                }
            }
        }
        System.out.println(this.array);
        threeCrip();
    }

    //faz a terceira criptografia
    public void threeCrip() {
        int cont = 0;
        char aux;
        Stack<Character> pilhaAux = new StaticStack<>(3);
        StaticList<Character> listaAux = new StaticList<>(3);

        for (int i = 0; i < this.array.length; i++) {
            if (!pilhaAux.isFull()) {
                pilhaAux.push(array[i]);
            }
            if (pilhaAux.isFull()) {
                aux = pilhaAux.pop();
                while (!pilhaAux.isEmpty()) {
                    listaAux.insert(pilhaAux.pop(),cont);
                    cont++;
                }
                listaAux.insert(aux, cont);
                if (listaAux.isFull()) {
                    while (!listaAux.isEmpty()) {
                        array[cont] = listaAux.remove(i);
                        cont++;
                    }
                }
            }
        }
        System.out.println(this.array);
    }
}
