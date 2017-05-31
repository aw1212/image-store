package com.expedia.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ImageStoreTwo {

    private int imageKey;
    private final List<Integer> keys;
    private final Map<Integer, byte[]> images;
    private final Map<String, Integer> keyByName;

    public ImageStoreTwo() {
        images = new HashMap<>();
        keyByName = new HashMap<>();
        imageKey = -1;
        keys = new ArrayList<>();
    }

    /**
     * Inserts an image in the storeImage
     *
     * @param id -- The identifier of the image
     * @param content -- The content of the image
     */
    public void storeImage (String id, byte[] image) {
        if (id == null || image == null) {
            throw new NullPointerException();
        }
        Collection<byte[]> values = images.values();
        if (!values.contains(image)) {
            int newKey = imageKey++;
            images.put(newKey, image);
            keyByName.put(id, newKey);
            keys.add(newKey);
        } else {
            Integer oldKey = null;
            for (Map.Entry<Integer, byte[]> imageEntry : images.entrySet()) {
                if (Arrays.equals(imageEntry.getValue(), image)) {
                    oldKey = imageEntry.getKey();
                    break;
                }
            }
            if (oldKey == null) {
                throw new IllegalStateException("potato");
            }
            keyByName.put(id, oldKey);
        }
        removeImagesWithoutReferences();
    }

    private void removeImagesWithoutReferences() {
        for (Iterator<Integer> iterator = keys.iterator(); iterator.hasNext();) {
            Integer key = iterator.next();
            if (!keyByName.containsValue(key)) {
                images.remove(key);
                iterator.remove();
            }
        }
    }

    /**
     * Retrieves an image from the storeImage
     *
     * @param id -- The identifier of the image to be retrieved
     * @return the image content
     */
    public byte[] fetchImage(String id) {
        Integer imageKey = keyByName.get(id);
        return images.get(imageKey);
    }

    /**
     * The size of the storeImage
     *
     * @return the actual storeImage size
     */
    public int size() {
        return images.values().size();
    }
}
