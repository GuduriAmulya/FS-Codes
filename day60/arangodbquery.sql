/*
Fetch the names of all hospitals available in the system.

Output Format: hospital_name

Sampel Output:
--------------
"Apollo Hyderabad"
"AIIMS Delhi"

*/
for h in hospitals
return h.name

/*
Retrieve names of doctors who have more than 10 years of experience.

Output Format: doctor_name

Sampel Output:
--------------
"Dr. Arjun"

*/
for d in doctors
filter d.experience>10
return d.name

/*
Retrieve the names of patients who are treated by the doctor named "Dr. Arjun".

Output Format: patient_name

Sampel Output:
--------------
"Vikram"


*/
for d in doctors
filter d.name=="Dr. Arjun"
for p in inbound d treated_by
return p.name

/*
Retrieve the diseases diagnosed for the patient named "Anita".

Output Format: disease_name

Sampel Output:
--------------
"heart_disease"


*/

for p in patients
filter p.name=="Anita"
for d in outbound p diagnosed_with 
return d._key


/*
Retrieve names of doctors who prescribe drugs related to heart_disease.

Output Format: doctor_name

Sampel Output:
--------------
"Dr. Meera"


*/
for d in diseases
filter d._key=="heart_disease"
for drug in inbound d related_to
for doc in inbound drug prescribed
return doc.name

/*
Retrieve each patient along with:
    - Doctor treating them
    - Drug prescribed by that doctor

Output Format: patient | doctor | drug

Sampel Output:
--------------
{
  "patient": "Rahul",
  "doctor": "Dr. Arjun",
  "drug": "Imatinib"
}
{
  "patient": "Anita",
  "doctor": "Dr. Meera",
  "drug": "Aspirin"
}



*/

for p in patients
for d in outbound p treated_by
for drug in outbound d prescribed
return {
patient:p.name, 
doctor:d.name, 
drug:drug.name
}