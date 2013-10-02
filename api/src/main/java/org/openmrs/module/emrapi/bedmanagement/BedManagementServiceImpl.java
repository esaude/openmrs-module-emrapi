/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.emrapi.bedmanagement;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.emrapi.EmrApiConstants;

import java.util.List;

public class BedManagementServiceImpl extends BaseOpenmrsService implements BedManagementService {

    BedManagementDAO dao;

    public void setDao(BedManagementDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<AdmissionLocation> getAllAdmissionLocations() {
        return dao.getAllLocationsBy(EmrApiConstants.LOCATION_TAG_SUPPORTS_ADMISSION);
    }

    @Override
    public AdmissionLocation getLayoutForWard(String uuid) {
        return dao.getLayoutForWard(uuid);
    }
}
