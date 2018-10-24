/*
 * Copyright 2018 Roman Proshin
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
package org.proshin.finapi.account;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.apache.http.NameValuePair;

public interface QueryCriteria extends Iterable<NameValuePair> {

    QueryCriteria withIds(Iterable<Long> ids);

    QueryCriteria withSearch(String search);

    QueryCriteria withTypes(Type... types);

    QueryCriteria withBankConnections(Iterable<Long> ids);

    QueryCriteria withMinLastSuccessfulUpdate(OffsetDateTime minLastSuccessfulUpdate);

    QueryCriteria withMaxLastSuccessfulUpdate(OffsetDateTime maxLastSuccessfulUpdate);

    QueryCriteria withMinBalance(BigDecimal minBalance);

    QueryCriteria withMaxBalance(BigDecimal maxBalance);
}
