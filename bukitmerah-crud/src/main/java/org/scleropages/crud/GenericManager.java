/**
 * Copyright 2001-2005 The Apache Software Foundation.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.scleropages.crud;

import org.scleropages.crud.exception.BizStateViolationException;
import org.scleropages.crud.types.Available;

import javax.validation.Valid;

/**
 * Base manager for business models.
 * <p>
 * implementations must defined generic-type in class declaration. <br>
 * <pre>
 * M=model type
 * ID= id type
 * MP=model mapper type
 * </pre>
 *
 * @author <a href="mailto:martinmao@icloud.com">Martin Mao</a>
 */
public interface GenericManager<M, ID, MP> {

    /**
     * return default instance of {@link ModelMapper} in current class generic-type declaration.
     *
     * @return
     */
    default MP getModelMapper() {
        return (MP) ModelMapperRepository.getRequiredModelMapper(getClass());
    }

    /**
     * return instance of {@link ModelMapper} in class generic-type declaration by given {@link GenericManager} implementation.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    default <T> T getModelMapperByGenericManagerType(Class<GenericManager> genericManagerClass) {
        return (T) ModelMapperRepository.getRequiredModelMapper(genericManagerClass);
    }

    /**
     * return instance of {@link ModelMapper} in class generic-type declaration by given {@link ModelMapper} implementation.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    default <T> T getModelMapper(Class<T> modelMapperClass) {
        return (T) ModelMapperRepository.getRequiredModelMapper(modelMapperClass);
    }

}
