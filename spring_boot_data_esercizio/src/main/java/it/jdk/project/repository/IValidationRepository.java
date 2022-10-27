package it.jdk.project.repository;

import java.util.Optional;
import it.jdk.project.models.ValidationDTO;

/**
 *  @author  name
 *  @version 1.0
 *  @since   date
 */

public interface IValidationRepository {
    /**
     * Validating a user.
     *
     * @param validationDTO DTO validation info
     * @return DTO validation info
     * @throws RepoException Cannot validate User:
     * a) General error CODE R0000
     */
    ValidationDTO validateUser(ValidationDTO validationDTO) throws RepoException;

    /**
     * Get user validation info.
     *
     * @param userId User identifier
     * @return DTO user validation info
     * @throws RepoException Cannot get user validation info:
     *  a) General error CODE R0000
     */
    Optional<ValidationDTO> getValidation(Integer userId) throws RepoException;
    
    //usare questo metodo e lavorare a livello di logic bussiness per il controllo del nome
}
