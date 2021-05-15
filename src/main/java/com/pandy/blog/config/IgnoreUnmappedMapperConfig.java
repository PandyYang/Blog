package com.pandy.blog.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * @author Pandy
 */

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IgnoreUnmappedMapperConfig {}
