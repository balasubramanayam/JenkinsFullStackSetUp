import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import axios from "axios";

import "../App.css";
import { Link } from "react-router-dom";
import { getAll } from "./Actions";

function GetAll() {
  const Data = useSelector((state) => state.data.Data);
  const dispatch = useDispatch();

  const [deleteData,setDeleteData]=useState([]);

  useEffect(()=>{
    axios.get("http://localhost:8081/Data").then(
      (response)=>{dispatch(getAll(response.data))}
    ).catch()
  },[dispatch])

  const deleteHandler = (id) => {
    if (window.confirm("Are you sure to Delete?")) {
      axios
        .delete(`http://localhost:8081/Data/${id}`)
        .then(() => {
          setDeleteData(deleteData);
          window.location.reload(); 
        })
        .catch((err) => {
          console.log(err);
        });
    }
  };

  return (
    <div className="table-container">
      <table>
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Age</th>
            <th>delete</th>
            <th>Update</th>
            <th>View</th>
            <th><Link className="btn" to='/add'>Add</Link></th>
          </tr>
        </thead>
        <tbody>
          {Data.map((v) => (
            <tr key={v.id}>
              <td>{v.id}</td>
              <td>{v.name}</td>
              <td>{v.age}</td>
              <td><button className="btn" onClick={()=>{deleteHandler(v.id)}}>delete</button></td>
              <td>
                <Link className="btn" to={`update/${v.id}`}>
                  Update
                </Link>
              </td>
              <td>
                <Link className="btn" to={`GetById/${v.id}`}>
                  View
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default GetAll;
