package com.webauthn4j.converter;

import com.webauthn4j.client.CollectedClientData;
import com.webauthn4j.client.Origin;
import com.webauthn4j.client.challenge.DefaultChallenge;
import com.webauthn4j.util.Base64UrlUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static com.webauthn4j.client.CollectedClientData.TYPE_WEBAUTHN_CREATE;
import static com.webauthn4j.client.CollectedClientData.TYPE_WEBAUTHN_GET;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectedClientDataConverterTest {

    private CollectedClientDataConverter target = new CollectedClientDataConverter();

    @Test
    public void convert_deserialization_test() {
        String clientDataJson = "{\"challenge\":\"tk31UH1ETGGTPj33OhOMzw\",\"origin\":\"http://localhost:8080\",\"tokenBinding\":{\"status\":\"not-supported\"},\"type\":\"webauthn.get\"}";
        String clientDataBase64UrlString = Base64UrlUtil.encodeToString(clientDataJson.getBytes(StandardCharsets.UTF_8));
        CollectedClientData collectedClientData = target.convert(clientDataBase64UrlString);
        assertThat(collectedClientData.getType()).isEqualTo(TYPE_WEBAUTHN_GET);
        assertThat(collectedClientData.getChallenge()).isEqualTo(new DefaultChallenge("tk31UH1ETGGTPj33OhOMzw"));
        assertThat(collectedClientData.getOrigin()).isEqualTo(new Origin("http://localhost:8080"));
    }

    @Test
    public void convert_clientDataBase64UrlString_with_new_keys_test() {
        String clientDataJson = "{\"challenge\":\"Tgup0LZZQKinvtQcZFYdRw\",\"new_keys_may_be_added_here\":\"do not compare clientDataJSON against a template. See https://goo.gl/yabPex\",\"origin\":\"http://localhost:8080\",\"tokenBinding\":{\"status\":\"not-supported\"},\"type\":\"webauthn.create\"}";
        String clientDataBase64UrlString = Base64UrlUtil.encodeToString(clientDataJson.getBytes(StandardCharsets.UTF_8));
        CollectedClientData collectedClientData = target.convert(clientDataBase64UrlString);
        assertThat(collectedClientData.getType()).isEqualTo(TYPE_WEBAUTHN_CREATE);
        assertThat(collectedClientData.getChallenge()).isEqualTo(new DefaultChallenge("Tgup0LZZQKinvtQcZFYdRw"));
        assertThat(collectedClientData.getOrigin()).isEqualTo(new Origin("http://localhost:8080"));
    }
}