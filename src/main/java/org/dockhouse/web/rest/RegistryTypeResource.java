/**
 *  Copyright (C) 2015  Dockhouse project org. ( http://dockhouse.github.io/ )
 *
 *  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.dockhouse.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.dockhouse.domain.RegistryType;
import org.dockhouse.repository.RegistryTypeRepository;
import org.dockhouse.service.RegistryTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing types of registry.
 */
@RestController
@RequestMapping("/api")
public class RegistryTypeResource {

    private final Logger log = LoggerFactory.getLogger(RegistryTypeResource.class);

    @Inject
    private RegistryTypeRepository registryTypeRepository;

    @Inject
    private RegistryTypeService registryTypeService;

    /**
     * GET  /registry_types -> get all registry types.
     */
    @RequestMapping(value = "/registry_types",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    ResponseEntity<List<RegistryType>> getRegistryTypes() {
        log.debug("REST request to get all Registry Types");
        return new ResponseEntity<>(registryTypeService.getAll(),
        							HttpStatus.OK);
    }

    /**
     * GET  /registry_types/:id -> get the "id" registry type.
     */
    @RequestMapping(value = "/registry_types/{id}",
		    method = RequestMethod.GET,
		    produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    ResponseEntity<RegistryType> getRegistryType(@PathVariable String id) {
        log.debug("REST request to get Registry Type : {}", id);
        return registryTypeService.getOne(id)
            .map(registryType -> new ResponseEntity<>(registryType, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * POST  /registry_types -> Create a new registry type.
     */
    @RequestMapping(value = "/registry_types",
		    method = RequestMethod.POST,
		    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Timed
    public void createRegistryType(@RequestBody @Valid RegistryType registryType) {
        log.debug("REST request to save Registry Type : {}", registryType);
        registryTypeRepository.save(registryType);
    }

    /**
     * PUT  /registry_types/:id -> update an registry type.
     */
    @RequestMapping(value = "/registry_types/{id}",
		    method = RequestMethod.PUT,
		    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Timed
    public void updateRegistryType(@RequestBody @Valid RegistryType registryType,
    		                       @PathVariable String id) {
        log.debug("REST request to save Registry Type : {}", registryType);
        registryTypeRepository.save(registryType);
    }

    /**
     * DELETE  /registry_types/:id -> delete the "id" registry type.
     */
    @RequestMapping(value = "/registry_types/{id}",
		    method = RequestMethod.DELETE,
		    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Timed
    public void deleteRegistryType(@PathVariable String id) {
        log.debug("REST request to delete Registry Type: {}", id);
        registryTypeRepository.delete(id);
    }
}
