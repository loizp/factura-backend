package com.sunqubit.faqture.beans.utils;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.google.api.gax.paging.Page;
import com.google.appengine.tools.cloudstorage.GcsFileMetadata;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.BatchResult;
import com.google.cloud.ReadChannel;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.CopyWriter;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobGetOption;
import com.google.cloud.storage.Storage.BlobListOption;
import com.google.cloud.storage.Storage.BlobSourceOption;
import com.google.cloud.storage.Storage.BlobTargetOption;
import com.google.cloud.storage.Storage.BlobWriteOption;
import com.google.cloud.storage.Storage.BucketField;
import com.google.cloud.storage.Storage.BucketGetOption;
import com.google.cloud.storage.Storage.BucketListOption;
import com.google.cloud.storage.Storage.BucketSourceOption;
import com.google.cloud.storage.Storage.ComposeRequest;
import com.google.cloud.storage.Storage.CopyRequest;
import com.google.cloud.storage.Storage.SignUrlOption;
import com.google.cloud.storage.StorageBatch;
import com.google.cloud.storage.StorageBatchResult;
import com.google.cloud.storage.StorageClass;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.StorageOptions;
import com.google.common.net.MediaType;

public class StorageSnippets {
	private final Storage storage;

	  public StorageSnippets() {
	    this.storage = StorageOptions.getDefaultInstance().getService();
	  }

	  public Bucket createBucket(String bucketName) {
	    Bucket bucket = storage.create(BucketInfo.of(bucketName));
	    return bucket;
	  }

	  public Bucket createBucketWithStorageClassAndLocation(String bucketName) {
	    Bucket bucket = storage.create(BucketInfo.newBuilder(bucketName)
	        .setStorageClass(StorageClass.COLDLINE)
	        .setLocation("us")
	        .build());
	    return bucket;
	  }

	  public Blob createBlob(String bucketName, String blobName) {
	    BlobId blobId = BlobId.of(bucketName, blobName);
	    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();
	    Blob blob = storage.create(blobInfo);
	    return blob;
	  }

	  public Blob createBlobFromByteArray(String bucketName, String blobName) {
	    BlobId blobId = BlobId.of(bucketName, blobName);
	    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();
	    Blob blob = storage.create(blobInfo, "Hello, World!".getBytes(UTF_8));
	    return blob;
	  }

	  public Blob createBlobFromInputStream(String bucketName, String blobName) {
	    InputStream content = new ByteArrayInputStream("Hello, World!".getBytes(UTF_8));
	    BlobId blobId = BlobId.of(bucketName, blobName);
	    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();
	    Blob blob = storage.create(blobInfo, content);
	    return blob;
	  }

	  public Blob createEncryptedBlob(String bucketName, String blobName, String encryptionKey) {
	    InputStream content = new ByteArrayInputStream("Hello, World!".getBytes(UTF_8));

	    BlobId blobId = BlobId.of(bucketName, blobName);
	    BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
	        .setContentType("text/plain")
	        .build();
	    Blob blob = storage.create(blobInfo, content, BlobWriteOption.encryptionKey(encryptionKey));
	    return blob;
	  }

	  public Bucket getBucketWithMetageneration(String bucketName, long bucketMetageneration) {
	    Bucket bucket = storage.get(bucketName,
	        BucketGetOption.metagenerationMatch(bucketMetageneration));
	    return bucket;
	  }
	  
	  public Blob getBlobFromStringsWithMetageneration(String bucketName, String blobName,
	      long blobMetageneration) {
	    Blob blob = storage.get(bucketName, blobName,
	        BlobGetOption.metagenerationMatch(blobMetageneration));
	    return blob;
	  }

	  public Blob getBlobFromId(String bucketName, String blobName) {
	    BlobId blobId = BlobId.of(bucketName, blobName);
	    Blob blob = storage.get(blobId);
	    return blob;
	  }

	  public Blob getBlobFromIdWithMetageneration(String bucketName, String blobName,
	      long blobMetageneration) {
	    BlobId blobId = BlobId.of(bucketName, blobName);
	    Blob blob = storage.get(blobId, BlobGetOption.metagenerationMatch(blobMetageneration));
	    return blob;
	  }

	  public Page<Bucket> listBucketsWithSizeAndPrefix(String prefix) {
	    Page<Bucket> buckets = storage.list(BucketListOption.pageSize(100),
	        BucketListOption.prefix(prefix));
	    for (Bucket bucket : buckets.iterateAll()) {
	    }
	    return buckets;
	  }

	  public Page<Blob> listBlobsWithDirectoryAndPrefix(String bucketName, String directory) {
	    Page<Blob> blobs = storage.list(bucketName, BlobListOption.currentDirectory(),
	        BlobListOption.prefix(directory));
	    for (Blob blob : blobs.iterateAll()) {
	    }
	    return blobs;
	  }

	  public Bucket updateBucket(String bucketName) {
	    BucketInfo bucketInfo = BucketInfo.newBuilder(bucketName).setVersioningEnabled(true).build();
	    Bucket bucket = storage.update(bucketInfo);
	    return bucket;
	  }

	  public Blob updateBlob(String bucketName, String blobName) {
	    Map<String, String> newMetadata = new HashMap<>();
	    newMetadata.put("key", "value");
	    storage.update(BlobInfo.newBuilder(bucketName, blobName).setMetadata(null).build());
	    Blob blob = storage.update(BlobInfo.newBuilder(bucketName, blobName)
	        .setMetadata(newMetadata)
	        .build());
	    return blob;
	  }

	  public Blob updateBlobWithMetageneration(String bucketName, String blobName) {
	    Blob blob = storage.get(bucketName, blobName);
	    BlobInfo updatedInfo = blob.toBuilder().setContentType("text/plain").build();
	    storage.update(updatedInfo, BlobTargetOption.metagenerationMatch());
	    return blob;
	  }

	  public boolean deleteBucketWithMetageneration(String bucketName, long bucketMetageneration) {
	    boolean deleted = storage.delete(bucketName,
	        BucketSourceOption.metagenerationMatch(bucketMetageneration));
	    if (deleted) {
	    } else {
	    }
	    return deleted;
	  }

	  public boolean deleteBlobFromStringsWithGeneration(String bucketName, String blobName,
	      long blobGeneration) {
	    boolean deleted = storage.delete(bucketName, blobName,
	        BlobSourceOption.generationMatch(blobGeneration));
	    if (deleted) {
	    } else {
	    }
	    return deleted;
	  }

	  public boolean deleteBlobFromIdWithGeneration(String bucketName, String blobName,
	      long blobGeneration) {
	    BlobId blobId = BlobId.of(bucketName, blobName);
	    boolean deleted = storage.delete(blobId, BlobSourceOption.generationMatch(blobGeneration));
	    if (deleted) {
	    } else {
	    }
	    return deleted;
	  }

	  public boolean deleteBlob(String bucketName, String blobName) {
	    BlobId blobId = BlobId.of(bucketName, blobName);
	    boolean deleted = storage.delete(blobId);
	    if (deleted) {
	    } else {
	    }
	    return deleted;
	  }

	  public Blob composeBlobs(String bucketName, String blobName, String sourceBlob1,
	      String sourceBlob2) {
	    BlobId blobId = BlobId.of(bucketName, blobName);
	    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();
	    ComposeRequest request = ComposeRequest.newBuilder()
	        .setTarget(blobInfo)
	        .addSource(sourceBlob1)
	        .addSource(sourceBlob2)
	        .build();
	    Blob blob = storage.compose(request);
	    return blob;
	  }

	  public Blob copyBlob(String bucketName, String blobName, String copyBlobName) {
	    CopyRequest request = CopyRequest.newBuilder()
	        .setSource(BlobId.of(bucketName, blobName))
	        .setTarget(BlobId.of(bucketName, copyBlobName))
	        .build();
	    Blob blob = storage.copy(request).getResult();
	    return blob;
	  }

	  public Blob copyBlobInChunks(String bucketName, String blobName, String copyBlobName) {
	    CopyRequest request = CopyRequest.newBuilder()
	        .setSource(BlobId.of(bucketName, blobName))
	        .setTarget(BlobId.of(bucketName, copyBlobName))
	        .build();
	    CopyWriter copyWriter = storage.copy(request);
	    while (!copyWriter.isDone()) {
	      copyWriter.copyChunk();
	    }
	    Blob blob = copyWriter.getResult();
	    return blob;
	  }

	  public Blob rotateBlobEncryptionKey(
	      String bucketName, String blobName, String oldEncryptionKey, String newEncryptionKey) {
	    BlobId blobId = BlobId.of(bucketName, blobName);
	    CopyRequest request = CopyRequest.newBuilder()
	        .setSource(blobId)
	        .setSourceOptions(BlobSourceOption.decryptionKey(oldEncryptionKey))
	        .setTarget(blobId, BlobTargetOption.encryptionKey(newEncryptionKey))
	        .build();
	    Blob blob = storage.copy(request).getResult();
	    return blob;
	  }

	  public byte[] readBlobFromStringsWithGeneration(String bucketName, String blobName,
	      long blobGeneration) {
	    byte[] content = storage.readAllBytes(bucketName, blobName,
	        BlobSourceOption.generationMatch(blobGeneration));
	    return content;
	  }

	  public byte[] readBlobFromId(String bucketName, String blobName, long blobGeneration) {
	    BlobId blobId = BlobId.of(bucketName, blobName, blobGeneration);
	    byte[] content = storage.readAllBytes(blobId);
	    return content;
	  }

	  public byte[] readEncryptedBlob(String bucketName, String blobName, String decryptionKey) {
	    byte[] content = storage.readAllBytes(
	        bucketName, blobName, BlobSourceOption.decryptionKey(decryptionKey));
	    return content;
	  }

	  public Blob batch(String bucketName, String blobName1, String blobName2) {
	    StorageBatch batch = storage.batch();
	    BlobId firstBlob = BlobId.of(bucketName, blobName1);
	    BlobId secondBlob = BlobId.of(bucketName, blobName2);
	    batch.delete(firstBlob).notify(new BatchResult.Callback<Boolean, StorageException>() {
	      public void success(Boolean result) {
	      }
	      public void error(StorageException exception) {
	      }
	    });
	    batch.update(BlobInfo.newBuilder(secondBlob).setContentType("text/plain").build());
	    StorageBatchResult<Blob> result = batch.get(secondBlob);
	    batch.submit();
	    Blob blob = result.get();
	    return blob;
	  }

	  public void readerFromStrings(String bucketName, String blobName) throws IOException {
	    try (ReadChannel reader = storage.reader(bucketName, blobName)) {
	      ByteBuffer bytes = ByteBuffer.allocate(64 * 1024);
	      while (reader.read(bytes) > 0) {
	        bytes.flip();
	        bytes.clear();
	      }
	    }
	  }

	  public void readerFromId(String bucketName, String blobName) throws IOException {
	    BlobId blobId = BlobId.of(bucketName, blobName);
	    try (ReadChannel reader = storage.reader(blobId)) {
	      ByteBuffer bytes = ByteBuffer.allocate(64 * 1024);
	      while (reader.read(bytes) > 0) {
	        bytes.flip();
	        bytes.clear();
	      }
	    }
	  }

	  public void writer(String bucketName, String blobName) throws IOException {
	    BlobId blobId = BlobId.of(bucketName, blobName);
	    byte[] content = "Hello, World!".getBytes(UTF_8);
	    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();
	    try (WriteChannel writer = storage.writer(blobInfo)) {
	      try {
	        writer.write(ByteBuffer.wrap(content, 0, content.length));
	      } catch (Exception ex) {
	      }
	    }
	  }

	  public URL signUrl(String bucketName, String blobName) {
	    URL signedUrl = storage.signUrl(BlobInfo.newBuilder(bucketName, blobName).build(), 14,
	        TimeUnit.DAYS);
	    return signedUrl;
	  }
	  
	  public URL signUrlWithSigner(String bucketName, String blobName, String keyPath)
	      throws IOException {
	    URL signedUrl = storage.signUrl(BlobInfo.newBuilder(bucketName, blobName).build(),
	        14, TimeUnit.DAYS, SignUrlOption.signWith(
	            ServiceAccountCredentials.fromStream(new FileInputStream(keyPath))));
	    return signedUrl;
	  }

	  public List<Blob> batchGet(String bucketName, String blobName1, String blobName2) {
	    BlobId firstBlob = BlobId.of(bucketName, blobName1);
	    BlobId secondBlob = BlobId.of(bucketName, blobName2);
	    List<Blob> blobs = storage.get(firstBlob, secondBlob);
	    return blobs;
	  }

	  public List<Blob> batchGetIterable(String bucketName, String blobName1, String blobName2) {
	    List<BlobId> blobIds = new LinkedList<>();
	    blobIds.add(BlobId.of(bucketName, blobName1));
	    blobIds.add(BlobId.of(bucketName, blobName2));
	    List<Blob> blobs = storage.get(blobIds);
	    return blobs;
	  }

	  public List<Blob> batchUpdate(String bucketName, String blobName1, String blobName2) {
	    Blob firstBlob = storage.get(bucketName, blobName1);
	    Blob secondBlob = storage.get(bucketName, blobName2);
	    List<Blob> updatedBlobs = storage.update(
	        firstBlob.toBuilder().setContentType("text/plain").build(),
	        secondBlob.toBuilder().setContentType("text/plain").build());
	    return updatedBlobs;
	  }

	  public List<Blob> batchUpdateIterable(String bucketName, String blobName1, String blobName2) {
	    Blob firstBlob = storage.get(bucketName, blobName1);
	    Blob secondBlob = storage.get(bucketName, blobName2);
	    List<BlobInfo> blobs = new LinkedList<>();
	    blobs.add(firstBlob.toBuilder().setContentType("text/plain").build());
	    blobs.add(secondBlob.toBuilder().setContentType("text/plain").build());
	    List<Blob> updatedBlobs = storage.update(blobs);
	    return updatedBlobs;
	  }

	  public List<Boolean> batchDelete(String bucketName, String blobName1, String blobName2) {
	    BlobId firstBlob = BlobId.of(bucketName, blobName1);
	    BlobId secondBlob = BlobId.of(bucketName, blobName2);
	    List<Boolean> deleted = storage.delete(firstBlob, secondBlob);
	    return deleted;
	  }

	  public List<Boolean> batchDeleteIterable(String bucketName, String blobName1, String blobName2) {
	    List<BlobId> blobIds = new LinkedList<>();
	    blobIds.add(BlobId.of(bucketName, blobName1));
	    blobIds.add(BlobId.of(bucketName, blobName2));
	    List<Boolean> deleted = storage.delete(blobIds);
	    return deleted;
	  }

	  public Acl getBucketAcl(String bucketName) {
	    Acl acl = storage.getAcl(bucketName, User.ofAllAuthenticatedUsers());
	    return acl;
	  }

	  public Acl getBucketAcl(String bucketName, String userEmail) {
	    Acl acl = storage.getAcl(bucketName, new User(userEmail));
	    return acl;
	  }

	  public boolean deleteBucketAcl(String bucketName) {
	    boolean deleted = storage.deleteAcl(bucketName, User.ofAllAuthenticatedUsers());
	    if (deleted) {
	    } else {
	    }
	    return deleted;
	  }

	  public Acl createBucketAcl(String bucketName) {
	    Acl acl = storage.createAcl(bucketName, Acl.of(User.ofAllAuthenticatedUsers(), Role.READER));
	    return acl;
	  }

	  public Acl updateBucketAcl(String bucketName) {
	    Acl acl = storage.updateAcl(bucketName, Acl.of(User.ofAllAuthenticatedUsers(), Role.OWNER));
	    return acl;
	  }

	  public List<Acl> listBucketAcls(String bucketName) {
	    List<Acl> acls = storage.listAcls(bucketName);
	    for (Acl acl : acls) {
	    }
	    return acls;
	  }

	  public Acl getDefaultBucketAcl(String bucketName) {
	    Acl acl = storage.getDefaultAcl(bucketName, User.ofAllAuthenticatedUsers());
	    return acl;
	  }

	  public boolean deleteDefaultBucketAcl(String bucketName) {
	    boolean deleted = storage.deleteDefaultAcl(bucketName, User.ofAllAuthenticatedUsers());
	    if (deleted) {
	    } else {
	    }
	    return deleted;
	  }

	  public Acl createDefaultBucketAcl(String bucketName) {
	    Acl acl =
	        storage.createDefaultAcl(bucketName, Acl.of(User.ofAllAuthenticatedUsers(), Role.READER));
	    return acl;
	  }

	  public Acl updateDefaultBucketAcl(String bucketName) {
	    Acl acl =
	        storage.updateDefaultAcl(bucketName, Acl.of(User.ofAllAuthenticatedUsers(), Role.OWNER));
	    return acl;
	  }

	  public List<Acl> listDefaultBucketAcls(String bucketName) {
	    List<Acl> acls = storage.listDefaultAcls(bucketName);
	    for (Acl acl : acls) {
	    }
	    return acls;
	  }

	  public Acl getBlobAcl(String bucketName, String blobName, long blobGeneration) {
	    BlobId blobId = BlobId.of(bucketName, blobName, blobGeneration);
	    Acl acl = storage.getAcl(blobId, User.ofAllAuthenticatedUsers());
	    return acl;
	  }

	  public Acl getBlobAcl(String bucketName, String blobName, String userEmail) {
	    BlobId blobId = BlobId.of(bucketName, blobName);
	    Acl acl = storage.getAcl(blobId, new User(userEmail));
	    return acl;
	  }

	  public boolean deleteBlobAcl(String bucketName, String blobName, long blobGeneration) {
	    BlobId blobId = BlobId.of(bucketName, blobName, blobGeneration);
	    boolean deleted = storage.deleteAcl(blobId, User.ofAllAuthenticatedUsers());
	    if (deleted) {
	    } else {
	    }
	    return deleted;
	  }

	  public Acl createBlobAcl(String bucketName, String blobName, long blobGeneration) {
	    BlobId blobId = BlobId.of(bucketName, blobName, blobGeneration);
	    Acl acl = storage.createAcl(blobId, Acl.of(User.ofAllAuthenticatedUsers(), Role.READER));
	    return acl;
	  }

	  public Acl updateBlobAcl(String bucketName, String blobName, long blobGeneration) {
	    BlobId blobId = BlobId.of(bucketName, blobName, blobGeneration);
	    Acl acl = storage.updateAcl(blobId, Acl.of(User.ofAllAuthenticatedUsers(), Role.OWNER));
	    return acl;
	  }

	  public Acl blobToPublicRead(String bucketName, String blobName, long blobGeneration) {
	    BlobId blobId = BlobId.of(bucketName, blobName, blobGeneration);
	    Acl acl = storage.createAcl(blobId, Acl.of(User.ofAllUsers(), Role.READER));
	    return acl;
	  }

	  public List<Acl> listBlobAcls(String bucketName, String blobName, long blobGeneration) {
	    BlobId blobId = BlobId.of(bucketName, blobName, blobGeneration);
	    List<Acl> acls = storage.listAcls(blobId);
	    for (Acl acl : acls) {
	    }
	    return acls;
	  }

	  public Page<Bucket> authListBuckets() {
	    Storage storage = StorageOptions.getDefaultInstance().getService();
	    Page<Bucket> buckets = storage.list();
	    for (Bucket bucket : buckets.iterateAll()) {
	    }
	    return buckets;
	  }

	  public Bucket enableRequesterPays(String  bucketName) throws StorageException {
	    Storage storage = StorageOptions.getDefaultInstance().getService();
	    BucketInfo bucketInfo = BucketInfo.newBuilder(bucketName)
	        .setRequesterPays(true)
	        .build();

	    Bucket bucket = storage.update(bucketInfo);
	    System.out.println("Requester pay status for " + bucketName +": " + bucket.requesterPays());
	    return bucket;
	  }

	  public Bucket disableRequesterPays(String bucketName) {
	    Storage storage = StorageOptions.getDefaultInstance().getService();
	    BucketInfo bucketInfo = BucketInfo.newBuilder(bucketName)
	        .setRequesterPays(false)
	        .build();
	    Bucket bucket = storage.update(bucketInfo);

	    System.out.println("Requester pays status for " + bucketName +": " + bucket.requesterPays());
	    return bucket;
	  }

	  public Bucket getRequesterPaysStatus(String bucketName) throws StorageException {
	    Storage storage = StorageOptions.getDefaultInstance().getService();
	    Bucket bucket = storage.get(bucketName,
	        Storage.BucketGetOption.fields(BucketField.BILLING));

	    System.out.println("Requester pays status : " + bucket.requesterPays());
	    return bucket;
	  }

	  public void downloadFileUsingRequesterPays(String projectId, String bucketName,
	      String srcFilename, Path destFilePath) throws IOException {
	    Storage storage = StorageOptions.getDefaultInstance().getService();
	    Blob blob = storage.get(BlobId.of(bucketName, srcFilename));
	    blob.downloadTo(destFilePath, Blob.BlobSourceOption.userProject(projectId));
	  }
	  
	  private final GcsService gcsService = GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());
	  
	  private void zipFiles(final GcsFilename... filesToZip) throws IOException {
			GcsFilename targetZipFile = new GcsFilename("bn", "mapatonGTFS.zip");
			final int fetchSize = 4 * 1024 * 1024;
			final int readSize = 2 * 1024 * 1024;
			GcsOutputChannel outputChannel = null;
			ZipOutputStream zip = null;
			try {
				GcsFileOptions options = new GcsFileOptions.Builder().mimeType(MediaType.ZIP.toString()).build();
				outputChannel = gcsService.createOrReplace(targetZipFile, options);
				zip = new ZipOutputStream(Channels.newOutputStream(outputChannel));
				GcsInputChannel readChannel = null;
				for (GcsFilename file : filesToZip) {
					try {
						final GcsFileMetadata meta = gcsService.getMetadata(file);
						if (meta == null) {
							continue;
						}
						ZipEntry entry = new ZipEntry(file.getObjectName());
						zip.putNextEntry(entry);
						readChannel = gcsService.openPrefetchingReadChannel(file, 0, fetchSize);
						final ByteBuffer buffer = ByteBuffer.allocate(readSize);
						int bytesRead = 0;
						while (bytesRead >= 0) {
							bytesRead = readChannel.read(buffer);
							buffer.flip();
							zip.write(buffer.array(), buffer.position(), buffer.limit());
							buffer.rewind();
							buffer.limit(buffer.capacity());
						}

					} finally {
						zip.closeEntry();
						readChannel.close();
					}
				}
			} finally {
				zip.flush();
				zip.close();
				outputChannel.close();
			}
		}
}
