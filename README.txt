Problem Description
===================

A hotel information store allows brands to upload images for their hotels; each brand has a different way to identify the images, so a hotel can have an image under different names depending on the brand.

Devise a data structure that stores each image efficiently and so it is not stored more than once. You can use a byte array in lieu of an actual image.

The sample project contains a class with the methods' signature for the image store.

You are required to:
1) Complete the implementation of the image store class.
2) Provide a way to demonstrate how the implementation works.

Additional notes:
- Please provide comments where you think they improve readability, but keep them compact.
- You can use any number of helper classes that you need to complete the solution.
- Please explain any assumptions you make


The following example shows the expected functionality:

1) Given the following sequence of operations:

   Insert <"Sunshine hotels", imageA>
   Insert <"Stormy hotels", imageA>
   Insert <"Beachfront Hotels", imageB>

The store should have size 2 and contain imageA and imageB.

2) Given the following sequence of operations:

   Insert <"Sunshine hotels", imageA>
   Insert <"Stormy hotels", imageA>
   Insert <"Sunshine hotels", imageB>
   Insert <"Stormy hotels", imageB>

The store should have size 1 and only contain imageB.


Solution Description
====================
Two solutions are provided.

ImageStore saves images in an array and draws on some elements of the ArrayList
data structure. Any image saved to the array also has its id saved to a map as the key with the image's position in the
array as the value.
If an image no longer has any ids referring to it, its location in the array is set to null.
When adding a new image to the array, the first null spot is filled, and if none are null, then the image is added
to the end of the array. Before adding new elements to the array, its size is checked and in the event that more space
is needed, the elements are copied into new array with additional space.

ImageStoreTwo is very similar but instead of saving images in an array, it saves them as values in a HashMap, where the
key of the image refers to a unique number. That same number is stored as the value in another map, there the
corresponding key is the id of the image.