package apoio;

/**
 * @author anderson.caye
 */
public class GemaPostgresSql {

    /**
     * Comandos para o SQL
     */
    private final static String INICIO = "";
    private final static String FIM = "";

    private final static String TUDO = "*";
    private final static String SELECT = "SELECT";
    private final static String FROM = "FROM";
    private final static String WHERE = "WHERE";

    private final static String ILIKE = "ILIKE";

    private final static String VALUES = "VALUES";
    private final static String INSERT = "INSERT INTO";

    private final static String UPDATE = "UPDATE";
    private final static String SET = "SET";
    
    
    /**
     * Valida o tamanho do trecho fornecido.
     *
     * @param trecho String - Trecho fornecido para comparação.
     * @param tamanho Int - Tamanho MINIMO para o trecho fornecido.
     * @return boolean - Se o trecho maior ou igual ao tamanho minimo, TRUE.
     */
    /*s
    private static boolean validacao(String trecho, int tamanho) {
        boolean result = false;

        if (trecho.length() >= tamanho) {
            result = true;
        }

        return result;
    }
    */

    /**
     * Valida o tamanho do trecho fornecido.
     *
     * @param trechos Vetor String - Trechos fornecidos para comparação.
     * @param tamanho Tamanho MINIMO para os trechos fornecido.
     * @return boolean - Se todos os trecho forem maior ou igual ao tamanho
     * minimo, TRUE.
     */
    private static boolean validacao(String[] trechos, int tamanho) {
        boolean result = false;
        String temp;

        for (int i = 0; i < trechos.length; i++) {
            temp = trechos[i];
            if (Gema.vazio(temp, tamanho)) {
                result = true;
            } else {
                result = false;
                break;
            }
        }

        return result;
    }

    /**
     *
     * @param lista
     * @return
     */
    private static String agruparVetor(String[] lista) {
        String list = "";

        for (int i = 0; i < lista.length; i++) {
            if (i != (lista.length - 1)) {
                list += lista[i] + ", ";
            } else {
                list += lista[i];
            }
        }

        return list;
    }

    private static String agruparVetor(String[] lista, String simb) {
        String list = "";

        for (int i = 0; i < lista.length; i++) {
            if (i != (lista.length - 1)) {
                list += simb + lista[i] + simb + ", ";
            } else {
                list += simb + lista[i] + simb;
            }
        }

        return list;
    }

    /**
     *
     * @param trecho
     * @return se tem letras retorna false se so tem numeros retorna true.
     */
    private static boolean pkNumeroOuPalavra(String trecho) {
        boolean result = true;

        for (int i = 0; i < trecho.length(); i++) {
            if (Character.isDigit(trecho.charAt(i)) == false) {
                result = false;
            }
        }

        return result;
    }

    /**
     * Pesquisa por algum fragmento uma informação contida em um campo da
     * stebela.
     *
     * @param tabela String - Nome da tabela onde deseja buscar a informação.
     * @param campo String - Coluna que deseja produrar. Pode conter mais de um,
     * nesse caso devem ser separados por vírgula.
     * @param criterio String - Trecho ou fragmento de trecho a ser procurado.
     * @return Retorna o código SQL para a busca.
     */
    public static String pesquisar(String tabela, String campo, String criterio) {
        String sql = null;
        String simb = "";
        if (Gema.vazio(tabela, 1) && Gema.vazio(criterio, 1)) {

            if (criterio.charAt(0) != '%' && criterio.charAt(criterio.length() - 1) != '%') {
                simb = "%";
            }

            sql
                    = INICIO
                    + SELECT + " " + TUDO + " "
                    + FROM + " " + tabela + " "
                    + WHERE + " " + campo + " "
                    + ILIKE + " '" + simb + criterio + simb + "'"
                    + FIM;

        }

        return sql;
    }

    /**
     *
     * @param tabela
     * @param campos
     * @param valores
     * @return
     */
    public static String insert(String tabela, String[] campos, String[] valores) {
        String sql = null;
        String campo;
        String valor;

        if ((campos.length == valores.length)
                && (Gema.vazio(tabela, 1) && validacao(campos, 1) && validacao(valores, 1))) {

            campo = agruparVetor(campos);
            valor = agruparVetor(valores, "'");

            sql
                    = INICIO
                    + INSERT + " "
                    + tabela + " "
                    + "(" + campo + ") "
                    + VALUES + " "
                    + "(" + valor + ")"
                    + FIM;
        }

        return sql;
    }

    /**
     *
     * @param pk
     * @param colunPk
     * @param tabela
     * @param campos
     * @param valores
     * @return
     */
    public static String update(Object pk, String colunPk, String tabela, String[] campos, String[] valores) {
        String sql = null;
        String campo;
        String valor;
        String pkText;

        if ((campos.length == valores.length)
                && (Gema.vazio(tabela, 1) && validacao(campos, 1) && validacao(valores, 1))) {

            campo = agruparVetor(campos);
            valor = agruparVetor(valores, "'");
            String atu = "";
            for (int i = 0; i < campos.length; i++) {
                if (i < campos.length - 1) {
                    atu += campos[i] + " = " + "'" + valores[i] + "'" + ", ";
                } else {
                    atu += campos[i] + " = " + "'" + valores[i] + "'";
                }
            }

            if (pkNumeroOuPalavra(pk.toString())) {// se verdadeiro é numero
                pkText = pk.toString();
            } else {
                pkText = "'" + pk.toString() + "'";
            }

            sql
                    = INICIO
                    + UPDATE + " "
                    + tabela + " "
                    + SET + " "
                    + atu + " "
                    + WHERE + " "
                    + colunPk + " = "
                    + pkText
                    + FIM;
        }

        return sql;
    }

    /**
     *
     * @param pk
     * @param tabela
     * @param campos
     * @param valores
     * @param join
     * @return
     */
    public static String update(Object pk, String colunPk, String tabela, String[] campos, String[] valores, String join) {
        String sql = null;
        return sql;
    }

    public static String delete(String tabela, String join) {
        String sql = null;
        sql = "DELETE FROM " + tabela + " WHERE "+ join;
        return sql;
    }

    public static String softDelete(Object pk, String tabela, String campo, String valor) {
        String sql = null;
        String[] campos = new String[1];
        String[] valores = new String[1];
        if (Gema.vazio(campo, 1) && Gema.vazio(valor, 1) && Gema.vazio(pk.toString(), 1)) {
            campos[0] = campo;
            valores[0] = valor;

            sql = update(pk, "", tabela, campos, valores);
        }
        return sql;
    }

    
}
