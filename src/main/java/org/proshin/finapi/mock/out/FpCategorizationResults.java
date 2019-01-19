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
package org.proshin.finapi.mock.out;

import org.json.JSONObject;
import org.proshin.finapi.accesstoken.UserAccessToken;
import org.proshin.finapi.endpoint.Endpoint;
import org.proshin.finapi.primitives.IterableJsonArray;

public final class FpCategorizationResults implements CategorizationResults {

    private final Endpoint endpoint;
    private final UserAccessToken token;
    private final JSONObject origin;

    public FpCategorizationResults(final Endpoint endpoint, final UserAccessToken token, final JSONObject origin) {
        this.endpoint = endpoint;
        this.token = token;
        this.origin = origin;
    }

    @Override
    public Iterable<CategorizationResult> results() {
        return new IterableJsonArray<>(
            this.origin.getJSONArray("categorizationCheckResult"),
            (array, index) -> new FpCategorizationResult(this.endpoint, this.token, array.getJSONObject(index))
        );
    }
}
