package dev.id.backend.web.controllers.specifics;

import dev.id.backend.data.entities.Entry;
import dev.id.backend.logic.dtos.specifics.EntryDto;
import dev.id.backend.logic.mappers.BaseMapper;
import dev.id.backend.logic.services.specifics.EntryService;
import dev.id.backend.web.controllers.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path ="/entry")
public class EntryController extends BaseController<Entry, EntryDto, Long> {

    @Autowired
    public EntryController(EntryService service, @Qualifier("entryMapper") BaseMapper<EntryDto, Entry> entryMapper) {
        super(service, entryMapper);
    }



}
