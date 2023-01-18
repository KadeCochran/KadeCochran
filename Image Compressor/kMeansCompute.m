function [means, clusters] = kMeansCompute(data, k, distFunc, numIter)
%Returns the means of the data along with the clusters of each data point
%using the disFunc function ( disFunc = @(a,b) norm(a-b)), a 2D array
%containing the data, the k number, and number of iterations it will
%compute the means.

numRows = size(data,1);

randNum = (randi(numRows));
kNew = data(randNum, :);

for i=2:k
    num = (randi(numRows));
    kNew = [kNew; data(num, :)];
end 

%Initializes the k values in kNew by picking random data points to fill
%kNew with.

for i=1:numIter

kOld = kNew;

%saves the old k values before changing.

         min = distFunc(data(1,:), kOld(1,:));
         kVal = 1;

        for l=1:k         
            
           if(min > ( distFunc(data(1,:),kOld(l,:))))
               min = distFunc(data(1,:),kOld(l,:));
               kVal = l;     
           end

        end


clusters = kVal;

    for j=2:numRows

         min = distFunc(data(j,:),kOld(1,:));
         kVal = 1;

        for l=1:k 
                  
            
           if(min > ( distFunc(data(j,:),kOld(l,:))))
               min = distFunc(data(j,:),kOld(l,:));
               kVal = l;     
           end   
           
        end
           clusters = [clusters; kVal ]; 
    end
%Organizes the data into clusters by finding the minimum distance between
%points using disFunc, saved as min, and saves the location of the min val
%within the data and saves it into kVal. Uses if statements to compare and
%update the min vals and kVals to find the smallest min


firstClust = clusters == 1;
kNew = mean(data(firstClust,:));


    for m = 2:k
       
        clust = clusters == m; 
        kNew =[kNew; mean(data(clust,:))];
                   
    end

means = (kOld+kNew)./2;
%takes the averages of the clusters using the mean function, saves into
%kNew, then averages kOld and kNew and saves into means.

clusters = clusters;
end

