package com.hr.hrserver.service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;

public class S3ServiceB {
    Region region = Region.US_EAST_2;
    String bucket = "bfsteamproject";

    public static void prepareBucket(S3Client s3Client, String bucketName, Region region) {
        try {
            s3Client.createBucket(CreateBucketRequest
                    .builder()
                    .bucket(bucketName)
                    .createBucketConfiguration(
                            CreateBucketConfiguration.builder()
                                    .locationConstraint(region.id())
                                    .build())
                    .build());
            System.out.println("Creating bucket: " + bucketName);
            s3Client.waiter().waitUntilBucketExists(HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build());
            System.out.println(bucketName + " is ready.");
            System.out.printf("%n");
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    //download to local
    public void getFromS3(String localPath, String keyName) {
        S3Client s3 = S3Client.builder().region(region).build();
        try {
            File file = new File(localPath);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        s3.getObject(GetObjectRequest.builder().bucket(bucket).key(keyName)
                        .build(),
                ResponseTransformer.toFile(Paths.get(localPath))
        );
        s3.close();
    }

    //upload from local
    public void putToS3(String localPath, String keyName) {
        S3Client s3 = S3Client.builder().region(region).build();
        s3.putObject(PutObjectRequest.builder().bucket(bucket).key(keyName)
                        .build(),
                RequestBody.fromFile(Paths.get(localPath)));
        s3.close();
    }

//    public OutputStream getFromS3ToInputStream(String localPath, String keyName) {
//        S3Client s3 = S3Client.builder().region(region).build();
//        //OutputStream out = new OutputStreamWriter();
//        try {
//            File file = new File(localPath);
//            file.delete();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        s3.getObject(GetObjectRequest.builder().bucket(bucket).key(keyName)
//                        .build(),
//                ResponseTransformer.toInputStream()
//        );
//        s3.close();
//    }


    public void putFromInputStreamToS3(InputStream in, String keyName){
        S3Client s3 = S3Client.builder().region(region).build();
        s3.putObject(PutObjectRequest.builder().bucket(bucket).key(keyName)
                        .build(),
                RequestBody.fromInputStream(in,1024));
        s3.close();
    }
}
