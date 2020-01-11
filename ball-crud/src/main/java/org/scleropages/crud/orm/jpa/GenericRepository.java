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
package org.scleropages.crud.orm.jpa;

import org.scleropages.core.util.GenericTypes;
import org.scleropages.crud.orm.ModelMapper;
import org.scleropages.crud.orm.ModelMapperRepository;
import org.scleropages.crud.orm.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Map;

/**
 * @author <a href="mailto:martinmao@icloud.com">Martin Mao</a>
 */
public interface GenericRepository<M, MP, T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {


    default Page<M> findPage(Pageable pageable) {
        Page<T> entityPage = findAll(pageable);
        return entityPage.map(entity -> (M) ModelMapperRepository.getRequiredModelMapper(getClass()).mapForRead(entity));
    }

    default Page<M> findPage(Specification<T> spec, Pageable pageable) {
        Page<T> entityPage = findAll(spec, pageable);
        return entityPage.map(entity -> (M) ModelMapperRepository.getRequiredModelMapper(getClass()).mapForRead(entity));
    }

    default Page<M> findPage(Map<String, SearchFilter> searchFilters, Pageable pageable) {
        Specification<T> spec = DynamicSpecifications.bySearchFilter(searchFilters.values(), GenericTypes.getClassGenericType(getClass(), GenericRepository.class, 2));
        return findPage(spec, pageable);
    }

    default T saveModel(M model) {
        return save((T) ModelMapperRepository.getRequiredModelMapper(getClass()).mapForSave(model));
    }

    default M saveModelAndGet(M model) {
        ModelMapper requiredModelMapper = ModelMapperRepository.getRequiredModelMapper(getClass());
        return (M) requiredModelMapper.mapForRead(save((T) requiredModelMapper.mapForSave(model)));
    }

    default M findOneModel(Specification<T> spec) {
        return (M) ModelMapperRepository.getRequiredModelMapper(getClass()).mapForRead(findOne(spec));
    }

    default Iterable<M> findAllModel(Specification<T> spec) {
        return ModelMapperRepository.getRequiredModelMapper(getClass()).mapForReads(findAll(spec));
    }

    default M findModelById(ID id) {
        return (M) ModelMapperRepository.getRequiredModelMapper(getClass()).mapForRead(findById(id));
    }
}
