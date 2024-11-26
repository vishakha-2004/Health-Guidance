import numpy as np
from sklearn.model_selection import train_test_split
from sklearn import preprocessing
from sklearn.metrics import accuracy_score, confusion_matrix
from sklearn.svm import SVC
from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.naive_bayes import MultinomialNB
import pickle
import pandas as pd
from main import svc, get_predicted_value, helper  # importing functions from main.py

# Load the dataset
df = pd.read_csv('Training.csv')
X = df.iloc[:, :-1]   # : means to select all rows and :-1 means to select all columns except the last one.
y = df['prognosis']

# Encode target variable ......... categorical data into numerical data
le = preprocessing.LabelEncoder()
y_encoded = le.fit_transform(y)

# Split data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y_encoded, test_size=0.3, random_state=42)

# Define models and the code will iterate over this models dictionary to train and evaluate each model on the dataset
models = {
    'SVC': SVC(),
    'RandomForest': RandomForestClassifier(), #creating an instance of the RandomForestClassifier and assigning it to key 'RandomForest'
    'GradientBoosting': GradientBoostingClassifier(),
    'KNeighbors': KNeighborsClassifier(),
    'NaiveBayes': MultinomialNB()
}

# Train and evaluate models......name will take on the string key like 'SVC', 'RandomForest' and model will take on the corresponding classifier instance
for name, model in models.items():
    model.fit(X_train, y_train)
    y_pred = model.predict(X_test)
    accuracy = accuracy_score(y_test, y_pred)
    print(f'{name} accuracy: {accuracy:.2f}')
    print(f'{name} confusion matrix:\n{confusion_matrix(y_test, y_pred)}\n')

# Save the best performing model (SVC in this case)
with open('svc.pkl', 'wb') as model_file:
    pickle.dump(models['SVC'], model_file)

# Test the final SVC model (prediction done in main.py)
test_symptoms = ['cough', 'high_fever', 'sore_throat']  # Example input
disease_predicted = get_predicted_value(test_symptoms)  # Prediction logic in main.py
disease_info = helper(disease_predicted)  # Get disease details from main.py

print(f'Predicted Disease: {disease_predicted}')
print('Details:', disease_info)
