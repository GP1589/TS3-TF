/*
 *   Copyright 2019 Jasha Joachimsthal
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

 package com.example.tfts3.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "sbtfragments")
public class ProjectProperties {

  @NotEmpty
  private String citiesFile = "classpath:cities.json";

public String getCitiesFile() {
	return citiesFile;
}

public void setCitiesFile(String citiesFile) {
	this.citiesFile = citiesFile;
}
  
  
  
}