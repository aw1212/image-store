package com.expedia.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ImageStore {

    private static final int GROWTH_AMOUNT = 10;
    private static final int INITIAL_SIZE = 14;

    private final List<Integer> indices;
    private final Map<String, Integer> keyByName;
    private Object[] images;
    private int size;

    public ImageStore() {
        keyByName = new HashMap<>();
        indices = new ArrayList<>();
        images = new Object[INITIAL_SIZE];
        size = 0;
    }

    /**
     * Inserts an image in the storeImage
     *
     * @param id -- The identifier of the image
     * @param content -- The content of the image
     */
    public void storeImage(String id, byte[] image) {
        if (id == null || image == null) {
            throw new NullPointerException();
        }
        ensureCapacity(size + 1);
        if (size == 0) {
            addFirstElement(id, image);
        } else {
            int index = -1;
            for (int i = 0; i < size; i++) {
                if (Arrays.equals((byte[]) images[i], image)) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                int newIndex = getFirstAvailableSlot();
                images[newIndex] = image;
                keyByName.put(id, newIndex);
                indices.add(newIndex);
                size++;
            } else {
                keyByName.put(id, index);
            }
        }
        removeImagesWithoutReferences();
    }

    private void ensureCapacity(int minSize) {
        if (minSize - images.length > 0)  {
            images = Arrays.copyOf(images, (images.length + GROWTH_AMOUNT));
        }
    }

    private void addFirstElement(String id, byte[] image) {
        images[0] = image;
        keyByName.put(id, 0);
        indices.add(0);
        size++;
    }

    private int getFirstAvailableSlot() {
        for (int i = 0; i < size; i++) {
            if (images[i] == null) {
                return i;
            }
        }
        return size;
    }

    private void removeImagesWithoutReferences() {
        for (Iterator<Integer> iterator = indices.iterator(); iterator.hasNext();) {
            Integer index = iterator.next();
            if (!keyByName.containsValue(index)) {
                images[index] = null;
                iterator.remove();
                size--;
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
        return (byte[]) images[keyByName.get(id)];
    }

    /**
     * The size of the storeImage
     *
     * @return the actual storeImage size
     */
    public int size() {
        return size;
    }

}
