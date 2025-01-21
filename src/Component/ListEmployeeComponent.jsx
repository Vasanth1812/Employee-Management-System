import React,{useEffect, useState} from 'react'
import { listEmployees } from '../Services/EmployeeAervice'
import { useNavigate } from 'react-router-dom'
import { deleteEmployee } from '../Services/EmployeeAervice'


const ListEmployeeComponent = () => {
    const [employees , setemployees] = useState([])

    const navigator = useNavigate();

    useEffect(()=> {
       getallEmployees();
    },[])

    function getallEmployees(){
        listEmployees().then((response) => {
            setemployees(response.data);
        }).catch(error => {
            console.error(error);
        })
    }
 
    function addnewEmployee(){
        navigator('/add-employee')
    }

    function updateEmployee(id){
        navigator(`/update-employee/${id}`)
    }

    function removeEmployee(id){
        console.log(id);
        deleteEmployee(id).then((response) => {
            getallEmployees();
        }).catch(error =>{
            console.error(error);
        })
    }

  return (
    <div className='container'>
         <h2>   </h2>
         <button className='btn btn-primary mb-2' onClick={addnewEmployee}>Add Employee</button>
        <table className='table table-bordered table-striped'>
            <thead>
                <tr>
                    <th>Employee id</th>
                    <th>First-Name</th>
                    <th>Last-Name</th>
                    <th>E-mail</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>
                {
                employees.map(employee =>

                  <tr key={employee.id}>
                    <td>{employee.id}</td>
                    <td>{employee.firstName}</td>
                    <td>{employee.lastName}</td>
                    <td>{employee.email}</td>
                    <td>
                        <button className='btn btn-info' onClick={()=> updateEmployee(employee.id)}>Update</button> 
                        <button className='btn btn-danger' onClick={()=> removeEmployee(employee.id)}
                            style={{marginLeft:'10px'}}>Delete</button>
                    </td>
                  </tr>
                )
            }
            </tbody>
        </table>
    </div>
  )
}

export default ListEmployeeComponent