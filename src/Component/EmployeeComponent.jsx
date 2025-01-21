import React, { useEffect, useState } from 'react'
import { createEmployee, getEmployee, updateEmployee } from '../Services/EmployeeAervice'
import { useNavigate, useParams } from 'react-router-dom'

const EmployeeComponent = () => {
    const [firstName , setfirstName] = useState('')
    const [lastName , setlastName] = useState('')
    const [email , setemail] = useState('')

    const {id} = useParams();

    const [errors , seterrors] = useState ({
        firstName:'',
        lastName:'',
        email:''
    })

    const navigator = useNavigate();

    useEffect(() => {
        if(id){
            getEmployee(id).then((response) => {
                setfirstName(response.data.firstName)
                setlastName(response.data.lastName)
                setemail(response.data.email)
            }).catch(error => {
                console.error(error);
            })
        }
    },[id])

   function handleFirstName(e){
    setfirstName(e.target.value);
   }

   function handlelastName(e){
    setlastName(e.target.value);
   }

   function handleemail(e){
    setemail(e.target.value);
   }

   function saveOrupdateEmployee(e){
    e.preventDefault();

    if(validateform()){
        const employee = {firstName, lastName, email}
         console.log(employee);
       
         if(id){
            updateEmployee(id,employee).then((response) =>{
                   console.log(response.data);
                   navigator('/employees')
            }).catch(error => {
                console.error(error);
            })
         }else {
       createEmployee(employee).then((response) => {
        console.log(response.data);
        navigator('/employees')
       }).catch(error => {
        console.error(error);
       })
    }
   
   }
   }
   function validateform(){
    let valid=true;

    const errorscopy = {... errors};
    if(firstName.trim()){
        errorscopy.firstName = '';
    }else{
        errorscopy.firstName = 'First Name is required';
        valid=false;
    }

    if(lastName.trim()){
        errorscopy.lastName = '';
    }else{
        errorscopy.lastName = 'Last Name is required';
        valid=false;
    }

    if(email.trim()){
        errorscopy.email = '';
    }else{
        errorscopy.email = 'Email is required';
        valid=false;
    }

    seterrors(errorscopy);
    return valid;
   }

   function pageTitle(){
    if(id){
        return    <h2 className='text-center'>Update Employee</h2>
    }else{
        return    <h2 className='text-center'>Add Employee</h2>
    }
   }
   
  return (
    <div>
        <div className='container'>
        <br /> <br />
            <div className='row'>
                
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                    <br />
                      {pageTitle()}
                    <div className='card-body'>
                        <form action="">
                            <div className='form-group'>
                                <label className='form-label'>First Name</label>
                                <input type="text"
                                placeholder='Enter First Name' 
                                name='firstName'
                                value={firstName}
                             className={`form-control ${errors.firstName ? 'is-invalid':''}`}
                                onChange={handleFirstName}  > 
                                </input>
                                {errors.firstName && <div className='invalid-feedback'>{errors.firstName} </div>}
                            </div>


                            <div className='form-group'>
                                <label className='form-label'>Last Name</label>
                                <input type="text"
                                placeholder='Enter Last Name' 
                                name='lastName'
                                value={lastName}
                                className={`form-control ${errors.lastName ? 'is-invalid':''}`}
                                onChange={handlelastName}  />
                                 {errors.lastName && <div className='invalid-feedback'>{errors.lastName} </div>}
                            </div>

                            <div className='form-group'>
                                <label className='form-label'>E-mail</label>
                                <input type="text"
                                placeholder='Enter E-mail' 
                                name='email'
                                value={email}
                                className={`form-control ${errors.email ? 'is-invalid':''}`}
                                onChange={handleemail}  />
                                 {errors.email && <div className='invalid-feedback'>{errors.email} </div>}
                            </div>
                            <br />
                            <button className='btn btn-success' onClick={saveOrupdateEmployee}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default EmployeeComponent