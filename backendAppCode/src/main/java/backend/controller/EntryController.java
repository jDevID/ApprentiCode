package backend.controller;

import controllers.BaseController;
import domain.entity.Entry;
import domain.dto.EntryDto;
import mapper.BaseMapper;
import backend.service.EntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path ="/entry")
public class EntryController extends BaseController<Entry, EntryDto, Long> {

    @Autowired
    public EntryController(EntryService service, @Qualifier("entryMapper") BaseMapper<EntryDto, Entry> entryMapper) {
        super(service, entryMapper);
    }
    /*
    GET /api/v1/entries/search?filter=complexity:High&page=0&size=10&sort=id,asc
    GET /api/v1/entries/search?filter=tag:Java&page=0&size=10&sort=id,asc
    GET /api/v1/entries/search?filter=command:build&page=0&size=10&sort=id,asc
    GET /api/v1/entries/search?filter=active:true&page=0&size=10&sort=id,asc
    GET /api/v1/entries/search?filter=projectId:1&page=0&size=10&sort=id,asc
     */
    @GetMapping("/search")
    public ResponseEntity<Page<EntryDto>> search(@RequestParam(value = "filter", required = false) String filter,
                                                 @RequestParam(value = "tagName", required = false) String tagName,
                                                 @RequestParam(value = "complexityName", required = false) String complexityName,
                                                 @RequestParam(value = "command", required = false) String command,
                                                 @RequestParam(value = "active", required = false) Boolean active,
                                                 @RequestParam(value = "projectId", required = false) Long projectId,
                                                 Pageable pageable) {
        if (filter == null) {
            filter = "";
        }
        Page<EntryDto> entries = service.search(filter, tagName, complexityName, command, active, projectId, pageable);
        return ResponseEntity.ok(entries);
    }
}
