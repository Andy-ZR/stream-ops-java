package com.nanosai.streamops.storage.file;

import com.nanosai.streamops.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StreamStorageRootFSTest {


    @Test
    public void testConstructor() {
        StreamStorageRootFS streamStorageRootFS = new StreamStorageRootFS("data/test-root");

        assertEquals("data/test-root", streamStorageRootFS.getRootDirPath());
    }


    @Test
    public void testCreateStreamFileStorage() throws IOException {
        FileUtil.resetDir(new File("data/test-root/stream-1"));
        FileUtil.resetDir(new File("data/test-root"));

        StreamStorageRootFS streamStorageRootFS = new StreamStorageRootFS("data/test-root");

        assertTrue(new File("data/test-root").exists());

        StreamStorageFS streamStorageFS = streamStorageRootFS.createStreamStorage("stream-1");
        assertTrue(new File("data/test-root/stream-1").exists());

        assertNotNull(streamStorageFS);
        assertEquals("stream-1", streamStorageFS.getStreamId());
        assertEquals("data/test-root/stream-1", streamStorageFS.getRootDirPath());
    }

    @Test
    public void testCreateStreamFileStorageWithMaxBlockSize() throws IOException {
        FileUtil.resetDir(new File("data/test-root/stream-1"));
        FileUtil.resetDir(new File("data/test-root"));

        StreamStorageRootFS streamStorageRootFS = new StreamStorageRootFS("data/test-root");
        assertTrue(new File("data/test-root").exists());

        StreamStorageFS streamStorageFS = streamStorageRootFS.createStreamStorage("stream-1", 2 * 1024 * 1024);
        assertTrue(new File("data/test-root/stream-1").exists());

        assertEquals("stream-1", streamStorageFS.getStreamId());
        assertEquals("data/test-root/stream-1", streamStorageFS.getRootDirPath());
        assertEquals(2 * 1024 * 1024, streamStorageFS.getStorageFileBlockMaxSize());

    }
}
