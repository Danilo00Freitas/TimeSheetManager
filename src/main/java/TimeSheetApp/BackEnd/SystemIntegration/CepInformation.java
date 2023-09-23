package TimeSheetApp.BackEnd.SystemIntegration;

public class CepInformation {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public CepInformation(CepInformationRec cepInformationRec){

        this.cep = cepInformationRec.cep();
        this.logradouro = cepInformationRec.logradouro();
        this.complemento = cepInformationRec.complemento();
        this.bairro = cepInformationRec.bairro();
        this.localidade = cepInformationRec.localidade();
        this.uf = cepInformationRec.uf();
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
    public void setNumero(String numero){
        this.numero = numero;
    }

}
