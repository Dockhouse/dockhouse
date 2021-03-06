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
package org.dockhouse.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A type of registry.
 */
@Document(collection = "registry_types")
public class RegistryType extends AbstractAuditingEntity implements Serializable {

    @Id
    private String id;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    private String logo;

    @NotNull
    @Field("default_host")
    private String defaultHost;

    @Range(min = 0, max = 65535)
    @Field("default_port")
    private int defaultPort;

    @Field("public")
    private boolean isPublic;

    @NotNull
    @NotEmpty
    @Field("api_versions")
    private List<String> apiVersions;

    public String getId() {
    	return id;
    }

    public void setId(String id) {
    	this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDefaultHost() {
		return defaultHost;
	}

	public void setDefaultHost(String host) {
		this.defaultHost = host;
	}

	public int getDefaultPort() {
		return defaultPort;
	}

	public void setDefaultPort(int port) {
		this.defaultPort = port;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public List<String> getApiVersions() {
		return apiVersions;
	}

	public void setApiVersions(List<String> apiVersions) {
		this.apiVersions = apiVersions;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegistryType registryType = (RegistryType) o;

        if (id != null ? !id.equals(registryType.id) : registryType.id != null)
        	return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RegistryType{" +
	    "id='" + id + '\'' +
	    ", name='" + name + '\'' +
	    ", logo='" + logo + '\'' +
	    ", host='" + defaultHost + '\'' +
	    ", port='" + defaultPort + '\'' +
	    ", isPublic='" + isPublic + '\'' +
	    ", apiVersions='" + apiVersions + '\'' +
	    "}";
    }
}
