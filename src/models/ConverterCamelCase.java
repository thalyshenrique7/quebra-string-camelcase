package models;

import exception.ConverterException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConverterCamelCase {

    public ConverterCamelCase(String texto){

    }

    public static boolean stringValida(String texto){
        // texto não pode ser nulo e ser maior que 0 sem espaços em branco
        return ((texto != null) && (texto.trim().length() > 0));
    }

    public static int tamanho(String texto){
        // remove os espaços em branco e retorna o tamanho
        return texto.trim().length();
    }

    public static boolean contemNumeroNaString(String texto){
        // matches -> método de String booleano
        // .* -> significa qualquer número de caracteres (0 ou mais) no inicio e final da String
        // \\d+ um ou mais digitos númericos
        return (texto.matches("(.*)([0-9+])(.*)"));
    }

    public static boolean iniciaComNumero(String texto){
        // ^ -> inicio da String
        // .* -> significa qualquer número de caracteres (0 ou mais) no inicio e final da String
        // \\d -> digito numérico
        // Para permitir que inicie com - ou + basta acrescentar [-+]? -> "^[-+]?\\d.*"
        return texto.matches("^\\d.*");
    }

    public static boolean contemCaracterEspecial(String texto){
        // ^ -> inicio da String
        // ^a-zA-Z0-9 -> qualquer caractere que não seja uma letra maiúscula ou minúscula ou um número
        // .* -> significa qualquer número de caracteres (0 ou mais) no inicio e final da String
        return texto.matches("^[^a-zA-Z0-9]*");
    }

    public static List<String> converterCamelCase(String original) {
        if(!stringValida(original)){
            throw new ConverterException("Não é possível converter String null ou vazia.");
        } else if (contemCaracterEspecial(original)){
            throw new ConverterException("Não é possível converter String com caracteres especiais.");
        } else if (iniciaComNumero(original)){
            System.out.println("Não é possível converter String com números no inicio.");
        } else if (contemNumeroNaString(original)){
            return compiladorString(original);
        }

        // (?<=[a-z]) -> verifica se há letras minúsculas antes do ponto de divisão
        // (?<=[a-z]) -> verifica se há letras maiúsculas após o ponto de divisão
        // (?<=[A-Z][a-z]) -> verifica se há letras maiúsculas seguidas de letras minúsculas após ponto de divisão
        String[] stringSplit = original.split("(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])");

        return converterLowerCase(stringSplit);
    }

    public static List<String> compiladorString(String texto){
        // [A-Za-z]+ -> Sequência de uma ou mais letras maiúsculas ou minúsculas
        // [0-9]+ -> Sequência de um ou mais números
        // | -> operador lógico indica que qualquer uma das operações pode ser correspondida

        // Pattern -> define padrão de caracteres para procurar/substituir texto em uma String --> compile
        // Matcher -> procura um padrão de texto em uma string, criado a partir de um Pattern
        Pattern pattern = Pattern.compile("[A-Za-z]+|[0-9]+");
        Matcher matcher = pattern.matcher(texto);

        List<String> lista = new ArrayList<String>();

        // find -> obj do Matcher retorna true
        // group -> obj do Matcher é chamado para obter uma sequência de caracteres na posição atual
        // trim -> remove os espaços em branco
        while(matcher.find()){
            lista.add(matcher.group().trim());
        }

        String[] stringSplit = new String[lista.size()];
        return converterLowerCase(lista.toArray(stringSplit));
    }

    private static List<String> converterLowerCase(String[] array){
        List<String> lista = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            if(!array[i].isEmpty()){
                if(array[i].equals(array[i].toUpperCase())){
                    lista.add(array[i]);
                } else {
                    lista.add(array[i].toLowerCase());
                }
            }
        }
        return lista;
    }
}
