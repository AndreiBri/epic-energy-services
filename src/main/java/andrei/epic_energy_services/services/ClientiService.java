package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.Cliente;
import andrei.epic_energy_services.enums.FormaGiuridica;
import andrei.epic_energy_services.exceptions.NotFoundException;
import andrei.epic_energy_services.payloads.in_request.AggiornaClienteDTO;
import andrei.epic_energy_services.payloads.in_request.NuovoClienteMandatoDTO;
import andrei.epic_energy_services.payloads.in_response.ClienteDaMandareDTO;
import andrei.epic_energy_services.repositories.ClientiRepository;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Service
public class ClientiService {

    @Autowired
    private ClientiRepository clientiRepository;

    @Autowired
    private Cloudinary cloudinary;

    // ---------- GET tutti i clienti ----------
    public Page<ClienteDaMandareDTO> getAllClienti(
            int page, int size,
            BigDecimal fatturatoAnnuale,
            LocalDate dataInserimento,
            LocalDate dataUltimoContatto,
            String nome,
            String sortBy,
            String sortDir
    ) {
        Sort.Direction direction;
        if ("desc".equalsIgnoreCase(sortDir)) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }

        String sortField;
        if ("fatturatoAnnuale".equalsIgnoreCase(sortBy)) {
            sortField = "fatturatoAnnuale";
        } else if ("dataInserimento".equalsIgnoreCase(sortBy)) {
            sortField = "dataInserimento";
        } else if ("dataUltimoContatto".equalsIgnoreCase(sortBy)) {
            sortField = "dataUltimoContatto";
        } else {
            // default: ordina per ragione sociale
            sortField = "ragioneSociale";
        }

        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        return clientiRepository.findAllWithFilters(
                        fatturatoAnnuale, dataInserimento, dataUltimoContatto, nome, pageable)
                .map(c -> new ClienteDaMandareDTO(
                        c.getIdCliente(),
                        c.getRagioneSociale(),
                        c.getPartitaIva(),
                        c.getEmail(),
                        c.getPec(),
                        c.getTelefono(),
                        c.getEmailContatto(),
                        c.getNomeContatto(),
                        c.getCognomeContatto(),
                        c.getTelefonoContatto(),
                        c.getFatturatoAnnuale(),
                        c.getLogoAziendaleUrl(),
                        c.getFormaGiuridica(),
                        c.getDataInserimento(),
                        c.getDataUltimoContatto()
                ));
    }

    // ---------- GET singolo cliente ----------
    public ClienteDaMandareDTO getById(UUID id) {
        Cliente cliente = clientiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "Cliente"));
        return new ClienteDaMandareDTO(
                cliente.getIdCliente(),
                cliente.getRagioneSociale(),
                cliente.getPartitaIva(),
                cliente.getEmail(),
                cliente.getPec(),
                cliente.getTelefono(),
                cliente.getEmailContatto(),
                cliente.getNomeContatto(),
                cliente.getCognomeContatto(),
                cliente.getTelefonoContatto(),
                cliente.getFatturatoAnnuale(),
                cliente.getLogoAziendaleUrl(),
                cliente.getFormaGiuridica(),
                cliente.getDataInserimento(),
                cliente.getDataUltimoContatto()
        );
    }

    // ---------- POST nuovo cliente ----------
    // le date vengono impostate automaticamente alla data odierna
    public ClienteDaMandareDTO save(NuovoClienteMandatoDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(dto.ragioneSociale());
        cliente.setPartitaIva(dto.partitaIva());
        cliente.setEmail(dto.email());
        cliente.setPec(dto.pec());
        cliente.setTelefono(dto.telefono());
        cliente.setEmailContatto(dto.emailContatto());
        cliente.setNomeContatto(dto.nomeContatto());
        cliente.setCognomeContatto(dto.cognomeContatto());
        cliente.setTelefonoContatto(dto.telefonoContatto());
        cliente.setFatturatoAnnuale(dto.fatturatoAnnuale());
        cliente.setDataInserimento(LocalDate.now());
        cliente.setDataUltimoContatto(LocalDate.now());
        cliente.setLogoAziendaleUrl("https://placehold.co/150x150?text=logo");
        cliente.setFormaGiuridica((FormaGiuridica) dto.formaGiuridica());

        Cliente salvato = clientiRepository.save(cliente);

        return new ClienteDaMandareDTO(
                salvato.getIdCliente(),
                salvato.getRagioneSociale(),
                salvato.getPartitaIva(),
                salvato.getEmail(),
                salvato.getPec(),
                salvato.getTelefono(),
                salvato.getEmailContatto(),
                salvato.getNomeContatto(),
                salvato.getCognomeContatto(),
                salvato.getTelefonoContatto(),
                salvato.getFatturatoAnnuale(),
                salvato.getLogoAziendaleUrl(),
                salvato.getFormaGiuridica(),
                salvato.getDataInserimento(),
                salvato.getDataUltimoContatto()
        );
    }

    // ---------- PATCH cliente ----------
    // aggiorna solo i campi che vengono mandati nel body
    public ClienteDaMandareDTO update(UUID id, AggiornaClienteDTO dto) {
        Cliente cliente = clientiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "Cliente"));
        if (dto.ragioneSociale() != null) cliente.setRagioneSociale(dto.ragioneSociale());
        if (dto.partitaIva() != null) cliente.setPartitaIva(dto.partitaIva());
        if (dto.email() != null) cliente.setEmail(dto.email());
        if (dto.pec() != null) cliente.setPec(dto.pec());
        if (dto.telefono() != null) cliente.setTelefono(dto.telefono());
        if (dto.emailContatto() != null) cliente.setEmailContatto(dto.emailContatto());
        if (dto.nomeContatto() != null) cliente.setNomeContatto(dto.nomeContatto());
        if (dto.cognomeContatto() != null) cliente.setCognomeContatto(dto.cognomeContatto());
        if (dto.telefonoContatto() != null) cliente.setTelefonoContatto(dto.telefonoContatto());
        if (dto.fatturatoAnnuale() != null) cliente.setFatturatoAnnuale(dto.fatturatoAnnuale());
        if (dto.formaGiuridica() != null) cliente.setFormaGiuridica(dto.formaGiuridica());

        Cliente salvato = clientiRepository.save(cliente);

        return new ClienteDaMandareDTO(
                salvato.getIdCliente(),
                salvato.getRagioneSociale(),
                salvato.getPartitaIva(),
                salvato.getEmail(),
                salvato.getPec(),
                salvato.getTelefono(),
                salvato.getEmailContatto(),
                salvato.getNomeContatto(),
                salvato.getCognomeContatto(),
                salvato.getTelefonoContatto(),
                salvato.getFatturatoAnnuale(),
                salvato.getLogoAziendaleUrl(),
                salvato.getFormaGiuridica(),
                salvato.getDataInserimento(),
                salvato.getDataUltimoContatto()
        );
    }

    // ---------- PATCH logo cliente ----------
    public ClienteDaMandareDTO uploadLogo(UUID id, MultipartFile file) throws IOException {
        Cliente cliente = clientiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "Cliente"));

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of());
        cliente.setLogoAziendaleUrl((String) uploadResult.get("secure_url"));

        Cliente salvato = clientiRepository.save(cliente);

        return new ClienteDaMandareDTO(
                salvato.getIdCliente(),
                salvato.getRagioneSociale(),
                salvato.getPartitaIva(),
                salvato.getEmail(),
                salvato.getPec(),
                salvato.getTelefono(),
                salvato.getEmailContatto(),
                salvato.getNomeContatto(),
                salvato.getCognomeContatto(),
                salvato.getTelefonoContatto(),
                salvato.getFatturatoAnnuale(),
                salvato.getLogoAziendaleUrl(),
                salvato.getFormaGiuridica(),
                salvato.getDataInserimento(),
                salvato.getDataUltimoContatto()
        );
    }

    // ---------- DELETE cliente ----------
    public void delete(UUID id) {
        Cliente cliente = clientiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "Cliente"));
        clientiRepository.delete(cliente);
    }
}
