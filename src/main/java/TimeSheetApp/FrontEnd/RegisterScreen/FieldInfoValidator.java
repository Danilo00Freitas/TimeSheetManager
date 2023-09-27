package TimeSheetApp.FrontEnd.RegisterScreen;

import TimeSheetApp.BackEnd.SystemIntegration.CepInformation;
import TimeSheetApp.BackEnd.SystemIntegration.PersonalDataInformation;

public class FieldInfoValidator
{

    public void FieldInfoValidator(){}

    public boolean validatePDRSfields(PersonalDataInformation personalDataInformation){

        if (personalDataInformation.getCpf().isEmpty() ||
                personalDataInformation.getNome().isEmpty() ||
                personalDataInformation.getSetor().isEmpty() ||
                personalDataInformation.getCargo().isEmpty() ||
                personalDataInformation.getSuperior().isEmpty() ||
                personalDataInformation.getRotinaDeTrabalho().isEmpty() ||
                personalDataInformation.getGenero().isEmpty() ||
                personalDataInformation.getTelefone().isEmpty() ||
                personalDataInformation.getDataDeNascimento().isEmpty() ||
                personalDataInformation.getCpf() == null ||
                personalDataInformation.getNome() == null||
                personalDataInformation.getSetor() == null||
                personalDataInformation.getCargo() == null||
                personalDataInformation.getSuperior() == null ||
                personalDataInformation.getRotinaDeTrabalho() == null ||
                personalDataInformation.getGenero() == null||
                personalDataInformation.getTelefone() == null||
                personalDataInformation.getDataDeNascimento() == null)
        {
            return false;
        }else{
            return true;
        }
    }
    public boolean validateADRSfield(CepInformation cepInformation) {

        if (cepInformation.getCep().isEmpty() ||
                cepInformation.getBairro().isEmpty() ||
                cepInformation.getNumero().isEmpty() ||
                cepInformation.getLogradouro().isEmpty() ||
                cepInformation.getLocalidade().isEmpty() ||
                cepInformation.getUf().isEmpty() ||
                cepInformation.getCep() == null ||
                cepInformation.getBairro() == null  ||
                cepInformation.getNumero() == null  ||
                cepInformation.getLogradouro() == null  ||
                cepInformation.getLocalidade() == null ||
                cepInformation.getUf() == null ) {
            return false;
        } else {
            return true;
        }

    }
}
