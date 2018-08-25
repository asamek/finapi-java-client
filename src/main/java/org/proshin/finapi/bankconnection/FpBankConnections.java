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
package org.proshin.finapi.bankconnection;

import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.proshin.finapi.accesstoken.AccessToken;
import org.proshin.finapi.bankconnection.in.ImportParameters;
import org.proshin.finapi.bankconnection.in.UpdateParameters;
import org.proshin.finapi.endpoint.Endpoint;
import org.proshin.finapi.primitives.IterableJsonArray;

public final class FpBankConnections implements BankConnections {

    private final Endpoint endpoint;
    private final AccessToken token;

    public FpBankConnections(final Endpoint endpoint, final AccessToken token) {
        this.endpoint = endpoint;
        this.token = token;
    }

    @Override
    public BankConnection one(final Long id) {
        return new FpBankConnection(
            this.endpoint,
            this.token,
            new JSONObject(
                this.endpoint.get(
                    String.format("/api/v1/bankConnections/%d", id),
                    this.token
                )
            )
        );
    }

    @Override
    public Iterable<BankConnection> query(final Iterable<Long> ids) {
        return new IterableJsonArray<>(
            new JSONArray(
                this.endpoint.get(
                    "api/v1/bankConnections",
                    this.token,
                    new BasicNameValuePair("ids",
                        StreamSupport.stream(ids.spliterator(), false)
                            .map(Object::toString)
                            .collect(Collectors.joining(","))
                    )
                )
            ),
            (array, index) -> new FpBankConnection(this.endpoint, this.token, array.getJSONObject(index))
        );
    }

    @Override
    public Future<BankConnection> importNew(final ImportParameters parameters) {
        throw new UnsupportedOperationException("This method is not implemented yet");
    }

    @Override
    public Future<BankConnection> update(final UpdateParameters parameters) {
        throw new UnsupportedOperationException("This method is not implemented yet");
    }

    @Override
    public Iterable<Long> deleteAll() {
        throw new UnsupportedOperationException("This method is not implemented yet");
    }
}