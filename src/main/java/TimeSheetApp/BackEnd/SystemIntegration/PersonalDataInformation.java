package TimeSheetApp.BackEnd.SystemIntegration;

public class PersonalDataInformation {

    private String cpf;
    private String nome;
    private String setor;
    private String cargo;
    private String superior;
    private String rotinaDeTrabalho;
    private String genero;
    private String telefone;
    private String dataDeNascimento;

    public PersonalDataInformation(String cpf,String nome, String setor, String cargo, String superior,
                                   String rotinaDeTrabalho, String genero, String telefone, String dataDeNascimento){
        this.cpf = cpf;
        this.nome = nome;
        this.setor = setor;
        this.cargo = cargo;
        this.superior = superior;
        this.rotinaDeTrabalho = rotinaDeTrabalho;
        this.genero = genero;
        this.telefone = telefone;
        this.dataDeNascimento = dataDeNascimento;
    }


    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSetor() {
        return setor;
    }

    public String getCargo() {
        return cargo;
    }

    public String getSuperior() {
        return superior;
    }

    public String getRotinaDeTrabalho() {
        return rotinaDeTrabalho;
    }

    public String getGenero() {
        return genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }
}
