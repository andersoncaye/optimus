package apoio;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Representa uma data do calendário Gregoriano.
 * 
 * Com esta classe é possível criar e manipular datas, podendo realizar as
 * mais importantes operações que envolvem a contagem de tempo em dias.
 * 
 * A classe não opera com horas ou frações de dias.
 * 
 * @date   03/09/2013
 * @author Mouriac Diemer
 */
public class Data
{

    /**
     * Formato de data com dois dígitos para o dia e mes e quatro dígitos para o ano.
     */
    final static public int DDMMAAAA = 1;
    final static public int AAAAMMDD = 2;
    final static public int DIASEMANA_DDMMAAAA = 3;

    /**
     * Verifica se os valores informados para dia, mes e ano correspondem a uma data válida.
     * 
     * O valor para mes deve estar entre 1 e 12. O valor para dia deve ser maior
 do 1 e menor do que o maior dia válido para aquele mes. O ano pode ser
 qualquer valor positivo.
     * 
     * @param dia valor do dia a ser testado
     * @param mes valor do mes a ser testado
     * @param ano valor do ano a ser testado
     * @return verdadeiro se os valores correspondem a uma data válida.
     */
    public static boolean estáCorreta(int dia, int mes, int ano)
    {
        boolean ok = false;
        int[] dias =
        {
            31, 28, 30, 31, 30, 30, 31, 31, 30, 31, 30, 31
        };
        
        if (ano > 0)
        {
            if (Data.bissexto(ano))
            {
                dias[1] = 29;
            }
            if (mes >= 1 && mes <= 12)
            {
                if (dia >= 1 && dia <= dias[mes-1])
                {
                    ok = true;
                }
            }
        }
        return (ok);
    }

    /**
     * Verifica se o valor informado para o ano corresponde a um ano bissexto
     * 
     * Um ano será bissexto quando for divisível por 400 ou quando
     * for divisível por 4 e não for divisível por 100. A cada 4 anos
     * temos um ano bissexto, mas a cada 100 anos há uma exceção, ou seja,
     * os anos divisíveis por 100 não são bissextos. Todavia a cada 400 anos
     * temos outra exceção, ou seja, os anos divisívies por 400 são bissextos.
     * 
     * @param ano valor do ano a ser testado
     * @return verdadeiro quando o ano é bissexto
     */
    public static boolean bissexto(int ano)
    {
        return (ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0));
    }

    private static int obterÚltimoDiaDoMês(int mes)
    {
        int[] dias =
        {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };
        return (dias[mes - 1]);
    }
    
    private int dia;
    private int mes;
    private int ano;

    /**
     * Cria um novo objeto da classe Data
     *     
     * @param dia valor do dia para a data a ser criada
     * @param mes valor do mes para a data a ser criada
     * @param ano valor do ano para a data a ser criada
     */
    public Data(int dia, int mes, int ano)
    {
        definirComoHoje();
        definirData(dia, mes, ano);
    }

    public Data()
    {
        definirComoHoje();
    }
    
    public Data(int zero)
    {
        dia = 0;
        mes = 0;
        ano = 0;
    }
    
    public Data(String data)
    {
        definirComoHoje();
        String[] p = data.split("/");
        if (p.length == 3)
        {
            int d = Integer.parseInt(p[0]);
            int m = Integer.parseInt(p[1]);
            int a = Integer.parseInt(p[2]);
            definirData(d, m, a);
        }
    }

    public void definirData(int dia, int mes, int ano)
    {
        if (Data.estáCorreta(dia, mes, ano))
        {
            this.dia = dia;
            this.mes = mes;
            this.ano = ano;
        }
    }

    public void definirDia(int dia)
    {
        if (Data.estáCorreta(dia, this.mes, this.ano))
        {
            this.dia = dia;
        }
    }

    public void definirMês(int mes)
    {
        if (Data.estáCorreta(this.dia, mes, this.ano))
        {
            this.mes = mes;
        }
    }

    public void definirAno(int ano)
    {
        if (Data.estáCorreta(this.dia, this.mes, ano))
        {
            this.ano = ano;
        }
    }

    public boolean bissexto()
    {
        return (Data.bissexto(this.ano));
    }

    public void incrementarUmDia()
    {
        this.dia++;
        if (!Data.estáCorreta(this.dia, this.mes, this.ano))
        {
            // significa que ultrapassou o último dia do mes
            this.dia = 1;
            this.mes++;
            if (!Data.estáCorreta(this.dia, this.mes, this.ano))
            {
                // significa que ultrapassou o �ltimo mes do ano
                this.mes = 1;
                this.ano++;
            }
        }
    }

    public void incrementarDias(int dias)
    {
        for (int i = 0; i < dias; i++)
        {
            incrementarUmDia();
        }
    }

    public void decrementarUmDia()
    {
        this.dia--;
        if (this.dia <= 0) // significa que é preciso também voltar o mes
        {
            this.mes--;
            if (this.mes <= 0) // significa que é preciso voltar o ano
            {
                this.dia = 31;
                this.mes = 12;
                this.ano--;
            } else // seleciona o último dia do mes
            {
                this.dia = Data.obterÚltimoDiaDoMês(this.mes);
            }
        }
    }

    public void descrementarDias(int dias)
    {
        for (int i = 0; i < dias; i++)
        {
            decrementarUmDia();
        }
    }

    public int obterDia()
    {
        return (dia);
    }

    public int obterMês()
    {
        return (mes);
    }

    public int obterAno()
    {
        return (ano);
    }

    public String obterExtensoDoMês()
    {
        String[] meses =
        {
            "Janeiro", "Fevereiro", "Março", "Abril",
            "Maio", "Junho", "Julho", "Agosto", "Setembro",
            "Outubro", "Novembro", "Dezembro"
        };
        return (meses[this.mes-1]);
    }

    public String obterData() // retorna a data no formato dd/mm/aa
    {
        DecimalFormat df2 = new DecimalFormat("00");
        DecimalFormat df4 = new DecimalFormat("0000");
        return (df2.format(dia) + "/" + df2.format(mes) + "/" + df4.format(ano));
    }

    
    
    public String obterDataFormatada(int formato)
    {
        DecimalFormat df2 = new DecimalFormat("00");
        DecimalFormat df4 = new DecimalFormat("0000");
        String retorno = null;
        if (formato == 1)
        {
            retorno = df2.format(dia) + "/" + df2.format(mes) + "/" + df4.format(ano);
        } else if (formato == 2)
        {
            retorno = df4.format(ano) + "/" + df2.format(mes) + "/" + df2.format(dia);
        } else if (formato == 3)
        {
            retorno = this.obterDiaDaSemana() + ", " + df2.format(dia) + " de " + this.obterExtensoDoMês() + " de " + this.ano;
        }
        return retorno;
    }

    public String obterExtensoData() // retorna como no exemplo: "12 de setembro de 2006"
    {
        return (this.dia + " de " + obterExtensoDoMês() + " de " + this.ano);
    }

    public void definirComoHoje()
    {
        // Obter a data e hora do sistema
        Calendar data = Calendar.getInstance();
        this.dia = data.get(Calendar.DAY_OF_MONTH);
        this.mes = data.get(Calendar.MONTH)+1;
        this.ano = data.get(Calendar.YEAR);
    }

    public boolean equals(Data data)
    {
        return (this.dia == data.dia && this.mes == data.mes && this.ano == data.ano);
    }

    public Data criarDataIncrementada(int dias)
    {
        Data dtaux = new Data(dia, mes, ano);
        dtaux.incrementarDias(dias);
        return dtaux;
    }

    public int obterDiferença(Data data)
    {
        return Math.abs(this.obterDiaDoSéculo() - data.obterDiaDoSéculo());
    }

    private int obterDiaDoSéculo()
    {
        //www.inf.ufrgs.br/~cabral/Dia_do_Seculo.html
        int diaDoSéculo = (ano - 1901) * 365 + (ano - 1901) / 4 + dia + (mes - 1)
                * 31 - ((mes * 4 + 23) / 10)
                * ((mes + 12) / 15) + ((4 - ano % 4) / 4)
                * ((mes + 12) / 15);
        return diaDoSéculo;
    }

    public String obterDiaDaSemana()
    {
        String[] diasDaSemana =
        {
            "Segunda-feira","Terça-feira", "Quarta-feira", "Quinta-feira",
            "Sexta-feira", "Sábado", "Domingo"
        };
        int i = obterDiaDoSéculo() % 7;
        return diasDaSemana[i];
    }
    
    
    public int compareTo(Data data)
    {
        int retorno = 0;
        int x1 = this.obterDiaDoSéculo();
        int x2 = data.obterDiaDoSéculo();
        if (x1 > x2)
        {
            retorno = 1;
        }
        else if (x1 < x2)
        {
            retorno = -1;
        }
        return retorno;
    }
    
    public boolean isEmpty(){
        boolean empty = true;
        
        if(dia > 0 && mes > 0 && ano > 0){
            empty = false;
        }
        
        return empty;
    }
}
