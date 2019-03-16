/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sharplab.springframework.security.webauthn.sample.util.modelmapper;

import org.modelmapper.Provider;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

/**
 * Provider for PageImpl
 */
public class PageImplProvider implements Provider<PageImpl> {

    @SuppressWarnings("unchecked")
    @Override
    public PageImpl get(ProvisionRequest<PageImpl> request) {
        return new PageImpl(Collections.emptyList());
    }
}