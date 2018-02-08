#include<stdio.h>

void nsquare(int *arr, int size){
	for(int i=0; i<size; i++){
		for(int j=0; j<size; j++){
			if( (i!=j) && (arr[i]==arr[j])){
				printf("Duplicate found: %d\n", arr[i]);
				return;
			}
		}
	}
}
void n(int *arr, int size){
	int maxelement=0;
	for(int i=0; i<size; i++){
		if(arr[i] > maxelement)
			maxelement = arr[i];
	}
	int *count = (int *) malloc(sizeof(int) * (maxelement+1));
	for(int i=0; i<size; i++){
		count[arr[i]]++;
	}
	for(int i=0;i<size; i++){
		if(count[i] > 1)
			printf("Duplicate found: %d\n", i);
	}
}
int main(){
	int arr[7]={2, 3, 1, 5, 6, 4, 3};
	//nsquare(arr);
	n(arr, 7);
	return 0;
}
