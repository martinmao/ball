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
package org.scleropages.crud.types;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 对象化的map结构，便于文档工具生成，且提供 {@link Map} <->{@link EntryList}相互转换.
 *
 * @author <a href="mailto:martinmao@icloud.com">Martin Mao</a>
 */
public class EntryList<K, V> {

    private List<Entry<K, V>> entries;

    public static class Entry<K, V> {
        private K key;
        private V value;

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public List<Entry<K, V>> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry<K, V>> entries) {
        this.entries = entries;
    }

    public Map<K, V> toMap() {
        Map<K, V> map = Maps.newHashMap();
        if (null == entries)
            return map;
        entries.forEach(kvEntry -> map.put(kvEntry.getKey(), kvEntry.getValue()));
        return map;
    }

    public EntryList fromMap(Map<K, V> map) {
        if (map == null)
            return this;
        this.entries = Lists.newArrayList();
        map.forEach((k, v) -> {
            Entry<K, V> entry = new Entry<>();
            entry.setKey(entry.getKey());
            entry.setValue(entry.getValue());
            entries.add(entry);
        });
        return this;
    }
}
