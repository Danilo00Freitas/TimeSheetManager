package TimeSheetApp.BackEnd.SystemIntegration;

public class CepInformation {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public CepInformation(String cep,String logradouro, String complemento, String bairro,String localidade,String uf){
        this.cep = cep;
        this.logradouro = logradouro;
        /*this.numero = numero;*/
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }
    public String getNumero() {return numero; }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }
}
