A = imread('lmu small.png');
%Loads the image and saves as A

rows = (size(A, 1));

cols = (size(A, 2));

m = rows * cols;
%finds the total number of pixels and saves as m

B = reshape(A, [m,3]);
B = double(B);
%reshapes the image and turns its values into doubles and saves as B. 
%B must be in doubles for disFunc to work properly

[means, clusters] = kMeansCompute(B, 32, distFunc, 10);
%runs the kMeansCompute function with B, k=32, disFunc, and 10 iterations

for i = 1:m

    B(i,:) = means(clusters(i),:);
end
%changes data within B to equal the means calculated by kMeansCompute
%essentially compresses the data.

B = uint8(B);
%converts vals back into uint8.


compressed = reshape(B, [128, 128, 3]);
%reshapes B and saves compressed image as compressed

imagesc(compressed);
%loads compressed image