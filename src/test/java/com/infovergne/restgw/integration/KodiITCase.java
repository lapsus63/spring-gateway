package com.infovergne.restgw.integration;

import com.infovergne.restgw.api.HttpClientApi;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
public class KodiITCase {

    @Autowired
    private MockMvc mvc;
    @MockitoBean
    private HttpClientApi httpClient;

    @Test
    public void kodiPlaylistTest() throws Exception {
        // Given
        // When
        doNothing().when(httpClient).post(eq("http://kodi.url/jsonrpc"), any(JSONArray.class));
        // Then
        mvc.perform(MockMvcRequestBuilders.get("/kodi/playlist-victor?token=MY_TOKEN").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(httpClient, times(1)).post(eq("http://kodi.url/jsonrpc"), any(JSONArray.class));
    }

}
