using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using KryptografiaProjektKoncowy.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace KryptografiaProjektKoncowy.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class GcyController : ControllerBase
    {
        [HttpGet("{gCy}/{id}")]
        public ActionResult<bool> Gx(string gCy, string id)
        {
            try
            {
                Data.UsersList.FirstOrDefault(x => x.Id == Convert.ToInt32(id)).Gcy = Convert.ToInt32(gCy);
            }
            catch (NullReferenceException e)
            {
                return false;
            }

            return true;
        }

        [HttpGet]
        public ActionResult<List<User>> GetUsersList()
        {
            var all = Data.UsersList.FindAll(x => x.Gcy == -1);
            return all.Count > 0 ? null : Data.UsersList;
        }
    }
}